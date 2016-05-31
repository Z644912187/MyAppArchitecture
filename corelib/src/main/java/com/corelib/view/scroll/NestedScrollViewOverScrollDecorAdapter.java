package com.corelib.view.scroll;

import android.support.v4.widget.NestedScrollView;
import android.view.View;

import me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class NestedScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final NestedScrollView mView;

    public NestedScrollViewOverScrollDecorAdapter(NestedScrollView view) {
        mView = view;
    }

    @Override
    public View getView() {
        return mView;
    }

    @Override
    public boolean isInAbsoluteStart() {
        return !mView.canScrollVertically(-1);
    }

    @Override
    public boolean isInAbsoluteEnd() {
        return !mView.canScrollVertically(1);
    }
}
