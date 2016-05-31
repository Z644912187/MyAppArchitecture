package com.corelib.view.other;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.corelib.R;
import com.corelib.view.wheelview.WheelView;
import com.corelib.view.wheelview.adapter.ArrayWheelAdapter;

import java.util.Calendar;

/**
 * Created by wulingbo on 2016/4/7.
 */
public class SelectYearAndMonthWindow extends PopupWindow {
    //年
    private WheelView wl_y;
    //月
    private WheelView wl_m;

    private View pick_view;

    private Activity mContext;

    private String[] month_array = { "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" };
    private String[] year_array = new String[6];

    public interface ISelectYearAndMonthWindow{
        void selectedYearAndMonth(String year, String month);
    }

    private ISelectYearAndMonthWindow mISelectYearAndMonthWindow;
    public void setSelectYearAndMonthWindowListener(ISelectYearAndMonthWindow iSelectYearAndMonthWindow){
        mISelectYearAndMonthWindow = iSelectYearAndMonthWindow;
    }

    public SelectYearAndMonthWindow(Activity context) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        pick_view = inflater.inflate(R.layout.year_and_month_picker, null);
        wl_y = (WheelView)pick_view.findViewById(R.id.wl_y);
        wl_m = (WheelView)pick_view.findViewById(R.id.wl_m);

        //设置SelectPicPopupWindow的View
        this.setContentView(pick_view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);

        backgroundAlpha(0.5f);

        this.setOnDismissListener(new PoponDismissListener());
        initWheel();

    }

    private void initWheel(){
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        for (int i= 0; i<6 ; i++){
            year_array[i] = (curYear + i) + "年";
        }
        ArrayWheelAdapter<String> yearAdapter = new ArrayWheelAdapter<>(mContext, year_array);
        wl_y.setViewAdapter(yearAdapter);
        yearAdapter.setTextSize(18);


        ArrayWheelAdapter<String> monthAdapter = new ArrayWheelAdapter<>(mContext, month_array);
        wl_m.setViewAdapter(monthAdapter);
        monthAdapter.setTextSize(18);
        wl_m.setCyclic(true);

    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     * @author cg
     *
     */
    class PoponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            backgroundAlpha(1f);
            if (mISelectYearAndMonthWindow!=null){
                mISelectYearAndMonthWindow.selectedYearAndMonth(year_array[wl_y.getCurrentItem()] , month_array[wl_m.getCurrentItem()]);
            }
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mContext.getWindow().setAttributes(lp);
    }
}
