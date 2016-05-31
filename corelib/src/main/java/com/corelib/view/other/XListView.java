package com.corelib.view.other;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.*;

import com.corelib.utils.kit.PixelKit;

/**
 * 单个的下拉刷新控件
 */
public class XListView extends ListView implements AbsListView.OnScrollListener {
    private final static int SCROLL_BACK_HEADER = 0;
    private final static int SCROLL_BACK_FOOTER = 1;
    private final static int SCROLL_DURATION = 400; // scroll back duration
    private final static int PULL_LOAD_MORE_DELTA = 1; // when pull up >= 1px
    // at bottom, trigger
    // load more.
    private final static float OFFSET_RADIO = 1.8f; // support iOS like pull
    private float mLastY = -1; // save event y
    private Scroller mScroller; // used for scroll back

    // the interface to trigger refresh and load more.
    private Listener mListViewListener;
    private OnScrollListener mScrollListener; // user's scroll listener
    // -- header view
    private XListViewHeader mHeaderView;
    // header view content, use it to calculate the Header's height. And hide it
    // when disable pull refresh.
    private RelativeLayout mHeaderViewContent;
    private int mHeaderViewHeight; // header view's height
    private boolean mEnablePullRefresh = false;

    // -- footer view
    private XListViewFooter mFooterView;
    private boolean mEnablePullLoad = false;
    private boolean mIsFooterReady = false;

    // total list items, used to detect is at the bottom of listview.
    private int mTotalItemCount;

    // for mScroller, scroll back from header or footer.
    private int mScrollBack;
    //正在执行刷新动作
    private boolean pulling = false;

    /**
     * @param context
     */
    public XListView(Context context) {
        super(context);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWithContext(context);
    }

    private void initWithContext(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());
        // XListView need the scroll event, and it will dispatch the event to
        // user's listener (as a proxy).
        super.setOnScrollListener(this);

        // init header view
        mHeaderView = new XListViewHeader(context);
        mHeaderViewContent = mHeaderView.mPBContainer;
        addHeaderView(mHeaderView);

        // init footer view
        mFooterView = new XListViewFooter(context);

        // init header height
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
                new OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mHeaderViewHeight = mHeaderViewContent.getHeight();
                        getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        // make sure XListViewFooter is the last footer view, and only add once.
        if (!mIsFooterReady) {
            mIsFooterReady = true;
            addFooterView(mFooterView);
        }
        super.setAdapter(adapter);
    }

    /**
     * enable or disable pull down refresh feature.
     *
     * @param enable
     */
    public void setPullRefreshEnable(boolean enable) {
        mEnablePullRefresh = enable;
        if (!mEnablePullRefresh) { // disable, hide the content
            mHeaderViewContent.setVisibility(INVISIBLE);
        } else {
            mHeaderViewContent.setVisibility(VISIBLE);
        }
    }

    /**
     * enable or disable pull up load more feature.
     *
     * @param enable
     */
    public void setPullLoadEnable(boolean enable) {
        mEnablePullLoad = enable;
        if (!mEnablePullLoad) {
            mFooterView.hide();
            mFooterView.setOnClickListener(null);
            //make sure "pull up" don't show a line in bottom when listview with one page
            setFooterDividersEnabled(false);
        } else {
            mFooterView.show();
            mFooterView.setState(XListViewFooter.STATE_NORMAL);
            //make sure "pull up" don't show a line in bottom when listview with one page
            setFooterDividersEnabled(true);
            // both "pull up" and "click" will invoke load more.
            mFooterView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startLoadMore();
                }
            });
        }
    }

    /**
     * stop refresh, reset header view.
     */
    public void stopRefresh() {
        Time time = new Time();
        time.setToNow();
        if (pulling) {
            pulling = false;
            resetHeaderHeight();
        }
    }

    /**
     * stop load more, reset footer view.
     */
    public void stopLoadMore() {
        if (pulling) {
            pulling = false;
            mFooterView.setState(XListViewFooter.STATE_NORMAL);
        }
    }

    private void invokeOnScrolling() {
        if (mScrollListener instanceof OnXScrollListener) {
            OnXScrollListener l = (OnXScrollListener) mScrollListener;
            l.onXScrolling(this);
        }
    }

    private void updateHeaderHeight(float delta) {
        mHeaderView.setVisiableHeight((int) delta
                + mHeaderView.getVisiableHeight());
        if (!pulling) { // 未处于刷新状态，更新箭头
            if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
                mHeaderView.setState(XListViewHeader.STATE_READY);
            } else {
                mHeaderView.setState(XListViewHeader.STATE_NORMAL);
            }
        }
        setSelection(0); // scroll to top each time
    }

    /**
     * reset header view's height.
     */
    private void resetHeaderHeight() {
        int height = mHeaderView.getVisiableHeight();
        if (height == 0) // not visible.
            return;
        // refreshing and header isn't shown fully. do nothing.
        if (pulling && height <= mHeaderViewHeight) {
            return;
        }
        int finalHeight = 0; // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (pulling && height > mHeaderViewHeight) {
            finalHeight = mHeaderViewHeight;
        }

        mScrollBack = SCROLL_BACK_HEADER;
        mScroller.startScroll(0, height, 0, finalHeight - height,
                SCROLL_DURATION);
        // trigger computeScroll
        invalidate();
    }

    private void updateFooterHeight(float delta) {
        int height = mFooterView.getBottomMargin() + (int) delta;
        if (mEnablePullLoad && !pulling) {
            if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
                // more.
                mFooterView.setState(XListViewFooter.STATE_READY);
            } else {
                mFooterView.setState(XListViewFooter.STATE_NORMAL);
            }
        }
        mFooterView.setBottomMargin(height);

//		setSelection(mTotalItemCount - 1); // scroll to bottom
    }

    private void resetFooterHeight() {
        int bottomMargin = mFooterView.getBottomMargin();
        if (bottomMargin > 0) {
            mScrollBack = SCROLL_BACK_FOOTER;
            mScroller.startScroll(0, bottomMargin, 0, -bottomMargin,
                    SCROLL_DURATION);
            invalidate();
        }
    }

    public void startRefresh() {
        pulling = true;
        mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
        if (mListViewListener != null) {
            mListViewListener.onRefresh();
        }
    }

    public void startLoadMore() {
        pulling = true;
        mFooterView.setState(XListViewFooter.STATE_LOADING);
        if (mListViewListener != null) {
            mListViewListener.onLoadMore();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (mEnablePullRefresh && getFirstVisiblePosition() == 0
                        && (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
                    // the first item is showing, header has shown or pull down.
                    updateHeaderHeight(deltaY / OFFSET_RADIO);
                    invokeOnScrolling();
                } else if (mEnablePullLoad && getLastVisiblePosition() == mTotalItemCount - 1
                        && (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
                    // last item, already pulled up or want to pull up.
                    updateFooterHeight(-deltaY / OFFSET_RADIO);
                }
                break;
            default:
                mLastY = -1; // reset
                if (mEnablePullRefresh && getFirstVisiblePosition() == 0 && mHeaderView.getVisiableHeight() > 0) {
                    // invoke refresh
                    if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
                        if (mEnablePullRefresh && !pulling) {
                            startRefresh();
                        }
                    }
                    resetHeaderHeight();
                } else if (mEnablePullLoad && getLastVisiblePosition() == mTotalItemCount - 1 && mFooterView.getBottomMargin() > 0) {
                    // invoke load more.
                    if (mEnablePullLoad
                            && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA
                            && !pulling) {
                        startLoadMore();
                    }
                    resetFooterHeight();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (mScrollBack == SCROLL_BACK_HEADER) {
                mHeaderView.setVisiableHeight(mScroller.getCurrY());
            } else {
                mFooterView.setBottomMargin(mScroller.getCurrY());
            }
            postInvalidate();
            invokeOnScrolling();
        }
        super.computeScroll();
    }

    public void setListener(Listener l) {
        mListViewListener = l;
    }

    public Listener getListener() {
        return mListViewListener;
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mScrollListener = l;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mScrollListener != null) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // send to user's listener
        mTotalItemCount = totalItemCount;
        if (mScrollListener != null) {
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
                    totalItemCount);
        }
    }

    /**
     * you can listen ListView.OnScrollListener or this one. it will invoke
     * onXScrolling when header/footer scroll back.
     */
    public interface OnXScrollListener extends OnScrollListener {
        public void onXScrolling(View view);
    }

    /**
     * implements this interface to get refresh/load more event.
     */
    public interface Listener {
        void onRefresh();

        void onLoadMore();
    }

    public static class XListViewFooter extends LinearLayout {
        public final static int STATE_NORMAL = 0;        //平常状态
        public final static int STATE_READY = 1;        //准备加载
        public final static int STATE_LOADING = 2;      //正在加载

        private Context mContext;

        private LinearLayout mContentView;

        public XListViewFooter(Context context) {
            super(context);
            initView(context);
        }

        public XListViewFooter(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView(context);
        }


        public void setState(int state) {
           /* if (state == STATE_READY) {
                mContentView.setVisibility(INVISIBLE);
            } else if (state == STATE_LOADING) {
                mContentView.setVisibility(VISIBLE);
            } else {
                mContentView.setVisibility(GONE);
            }*/
            if (state == STATE_READY || state == STATE_LOADING) {
                mContentView.setVisibility(VISIBLE);
            } else {
                mContentView.setVisibility(GONE);
            }
        }

        public int getBottomMargin() {
            LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
            return lp.bottomMargin;
        }

        public void setBottomMargin(int height) {
            if (height < 0) return;
            LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
            lp.bottomMargin = height;
            mContentView.setLayoutParams(lp);
        }

        /**
         * normal status
         */
        public void normal() {
            mContentView.setVisibility(GONE);
        }


        /**
         * loading status
         */
        public void loading() {
            mContentView.setVisibility(VISIBLE);
        }

        /**
         * hide footer when disable pull load more
         */
        public void hide() {
            LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
            lp.height = 0;
            mContentView.setLayoutParams(lp);
        }

        /**
         * show footer
         */
        public void show() {
            LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
            lp.height = LayoutParams.WRAP_CONTENT;
            mContentView.setLayoutParams(lp);
        }

        private void initView(Context context) {
            mContext = context;
            LinearLayout moreView = new LinearLayout(mContext);
            moreView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            //moreView.setBackgroundColor(getResources().getColor(R.color.base_bg));
            addView(moreView);

            mContentView = initLinearLayout(mContext);
            ProgressBar progressBar = initProgressBar(mContext);
            TextView tv = initLoadText(mContext);
            mContentView.addView(progressBar);
            mContentView.addView(tv);
            moreView.addView(mContentView);
        }

        //初始化mProgressBar外层容器
        private LinearLayout initLinearLayout(Context context) {
            LinearLayout ll = new LinearLayout(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll.setLayoutParams(layoutParams);
            ll.setGravity(Gravity.CENTER);
            ll.setPadding(PixelKit.dp2px(context, 10.0f), PixelKit.dp2px(context, 10.0f), PixelKit.dp2px(context, 10.0f), PixelKit.dp2px(context, 10.0f));
            ll.setVisibility(GONE);
            return ll;
        }

        //初始化进度条
        private ProgressBar initProgressBar(Context context) {
            ProgressBar pb = new ProgressBar(context);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(PixelKit.dp2px(context, 15.0f), PixelKit.dp2px(context, 15.0f));
            pb.setLayoutParams(layoutParams);
            return pb;
        }

        private TextView initLoadText(Context context) {
            TextView tv = new TextView(context);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(PixelKit.dp2px(context, 5.0f), 0, 0, 0);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            tv.setTextColor(Color.parseColor("#999999"));
            tv.setLayoutParams(layoutParams);
            tv.setText("正在加载...");
            return tv;
        }

    }

    /**
     * Created by Administrator on 2015/4/24.
     */
    public static class XListViewHeader extends LinearLayout {
        public final static int STATE_NORMAL = 0;
        public final static int STATE_READY = 1;
        public final static int STATE_REFRESHING = 2;
        public RelativeLayout mPBContainer;
        private LinearLayout mContainer;
        private ProgressBar mProgressBar;
        private int mState = STATE_NORMAL;

        public XListViewHeader(Context context) {
            super(context);
            initView(context);
        }

        public XListViewHeader(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView(context);
        }

        private void initView(Context context) {
            initRelativeLayout(context);
            initProgressBar(context);
            mPBContainer.addView(mProgressBar);

            // 初始情况，设置下拉刷新view高度为0
            LayoutParams lp = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 0);
            mContainer = new LinearLayout(context);
            //mContainer.setBackgroundColor(getResources().getColor(R.color.base_bg));
            mContainer.setGravity(Gravity.BOTTOM);
            mContainer.addView(mPBContainer);
            addView(mContainer, lp);
            setGravity(Gravity.BOTTOM);
        }

        //初始化mProgressBar外层容器
        private void initRelativeLayout(Context context) {
            mPBContainer = new RelativeLayout(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mPBContainer.setLayoutParams(layoutParams);
        }

        //初始化进度条
        private void initProgressBar(Context context) {
            mProgressBar = new ProgressBar(context);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(PixelKit.dp2px(context, 15.0f), PixelKit.dp2px(context, 15.0f));
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            mProgressBar.setLayoutParams(layoutParams);
        }

        public void setState(int state) {
            if (state == mState)
                return;

            if (state == STATE_REFRESHING) {
                // 显示进度
                mProgressBar.setVisibility(View.VISIBLE);
            } else {
                //关闭进度
                mProgressBar.setVisibility(View.INVISIBLE);
            }
            mState = state;
        }

        public int getVisiableHeight() {
            return mContainer.getHeight();
        }

        public void setVisiableHeight(int height) {
            if (height < 0)
                height = 0;
            LayoutParams lp = (LayoutParams) mContainer
                    .getLayoutParams();
            lp.height = height;
            mContainer.setLayoutParams(lp);
        }
    }
}
