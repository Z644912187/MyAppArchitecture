package com.corelib.view.other;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.corelib.R;
import com.corelib.view.wheelview.OnWheelChangedListener;
import com.corelib.view.wheelview.WheelView;
import com.corelib.view.wheelview.adapter.ArrayWheelAdapter;
import com.corelib.view.wheelview.adapter.NumericWheelAdapter;
import com.corelib.utils.kit.TimeKit;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


/**
 * Created by tuyx on 2016/4/6.
 */
public class DateTimePickerWindow extends PopupWindow {

    /**
     * 构造器
     */
    public static class Builder {
        Activity context;
        //确定监听
        Listener positiveListener;
        //取消监听
        Listener negativeListener;

        public Builder(Activity context) {
            this.context = context;
        }

        public Builder setPositiveListen(Listener positiveListener) {
            this.positiveListener = positiveListener;
            return this;
        }

        public Builder setNegativeListen(Listener negativeListener) {
            this.negativeListener = negativeListener;
            return this;
        }
        /**
         * 构造
         */
        public DateTimePickerWindow build() {
            return new DateTimePickerWindow(context, this);
        }
    }
    //弹出框
    View popupWindowView;
    //取消按钮
    TextView tv_popup_cancel;
    //确定按钮
    TextView tv_popup_ok;
    //年月日
    WheelView wl_ymd;
    //小时
    WheelView wl_hour;
    //分
    WheelView wl_min;


    private String ymdData[] = new String[720];

    String[] hour_start =
            { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                    "19", "20", "21", "22", "23" };
    String[] dynamic_hour_start;

    String[] minute_start =
            { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                    "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
                    "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54",
                    "55", "56", "57", "58", "59" };
    String[] dynamic_minute_start;

    private DateTimePickerWindow(Activity context, final Builder builder) {
        super(context);
        //initData
        {
            long currentTime = System.currentTimeMillis();
            long day = 24 * 60 * 60 * 1000;
            for (int i = 0; i < 720; i++) {
                long time = currentTime + day * i;
                ymdData[i] = TimeKit.getYMDTime(time);
            }
        }
        //initPop
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            popupWindowView = inflater.inflate(R.layout.popup_window_date_time_picker, null);
            this.setContentView(popupWindowView);
            this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            // 设置SelectPicPopupWindow弹出窗体可点击
            this.setFocusable(true);
            this.setOutsideTouchable(true);
            this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            initWheelView(popupWindowView, context);
            this.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });
            //取消
            {
                tv_popup_cancel = (TextView) popupWindowView.findViewById(R.id.tv_popup_cancel);
                if (builder.negativeListener != null) {
                    tv_popup_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DateTimePickerWindow.this.dismiss();
                            builder.negativeListener.click(null);
                        }
                    });
                }
            }
            //确定
            {
                tv_popup_ok = (TextView) popupWindowView.findViewById(R.id.tv_popup_ok);
                if (builder.positiveListener != null) {
                    tv_popup_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DateTimePickerWindow.this.dismiss();
                            String date = String.format("%s %s:%s",
                                                        ((ArrayWheelAdapter)wl_ymd.getViewAdapter()).getItemText(wl_ymd.getCurrentItem()),
                                                        ((NumericWheelAdapter)wl_hour.getViewAdapter()).getItemText(wl_hour.getCurrentItem()),
                                                        ((NumericWheelAdapter)wl_min.getViewAdapter()).getItemText(wl_min.getCurrentItem()));
                            builder.positiveListener.click(date);
                        }
                    });
                }
            }
        }
    }

    private void initWheelView(View view, final Activity context) {
        long currentTime = System.currentTimeMillis();
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        int curDate = c.get(Calendar.DATE);
        wl_ymd = (WheelView) view.findViewById(R.id.wl_ymd);
        wl_hour = (WheelView) view.findViewById(R.id.wl_hour);
        wl_min = (WheelView) view.findViewById(R.id.wl_min);

        ArrayWheelAdapter<String> weekAdapter = new ArrayWheelAdapter<>(context, ymdData);
        List<String> ymdList = Arrays.asList(ymdData);
        wl_ymd.setViewAdapter(weekAdapter);
        weekAdapter.setTextSize(18);
//        wl_ymd.setCyclic(true);
        wl_ymd.setCurrentItem(ymdList.indexOf(TimeKit.getYMDTime(System.currentTimeMillis())));
        wl_ymd.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (newValue == 0) {
                    //滚到今天
                    synchronousHourAndMinute(context, true);
                } else {
                    if (oldValue == 0) {
                        //前一天是今天，需要更新小时和分钟
                        synchronousHourAndMinute(context, false);
                    }
                }
            }
        });

        NumericWheelAdapter numericAdapter1 = new NumericWheelAdapter(context, TimeKit.get24Hour(currentTime), 23);
        numericAdapter1.setLabel("");
        numericAdapter1.setTextSize(18);
        wl_hour.setViewAdapter(numericAdapter1);
        wl_hour.setCyclic(true);// 可循环滚动
        wl_hour.setCurrentItem(0);
        wl_hour.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (wl_ymd.getCurrentItem() == 0) {
                    //今天
                    if (newValue == 0) {
                        //当前小时
                        synchronousMinute(context, true);
                    } else if (oldValue == 0) {
                        synchronousMinute(context, false);
                    }
                }
            }
        });

        NumericWheelAdapter numericAdapter2 = new NumericWheelAdapter(context, TimeKit.getMinute(currentTime), 59);
        numericAdapter2.setLabel("");
        numericAdapter2.setTextSize(18);
        wl_min.setViewAdapter(numericAdapter2);
        wl_min.setCyclic(true);// 可循环滚动
        wl_min.setCurrentItem(0);
    }

    /**
     * 同步现在之后的小时和分钟
     * @param context
     * @param today 是不是今天
     */
    void synchronousHourAndMinute(Activity context, boolean today) {
        long currentTime = System.currentTimeMillis();
        if (today) {
            NumericWheelAdapter hourNumericWheelAdapter = new NumericWheelAdapter(context, TimeKit.get24Hour(currentTime), 23);
            hourNumericWheelAdapter.setLabel("");
            wl_hour.setViewAdapter(hourNumericWheelAdapter);
            wl_hour.setCurrentItem(0);

            NumericWheelAdapter minuteNumericWheelAdapter = new NumericWheelAdapter(context, TimeKit.getMinute(currentTime), 59);
            minuteNumericWheelAdapter.setLabel("");
            wl_min.setViewAdapter(minuteNumericWheelAdapter);
            wl_min.setCurrentItem(0);
        } else {
            NumericWheelAdapter hourNumericWheelAdapter = new NumericWheelAdapter(context, 0, 23);
            hourNumericWheelAdapter.setLabel("");
            wl_hour.setViewAdapter(hourNumericWheelAdapter);
            List<String> asList = Arrays.asList(hour_start);
            int hour_index = asList.indexOf(TimeKit.get24HourStr(currentTime));
            wl_hour.setCurrentItem(hour_index);

            NumericWheelAdapter minuteNumericWheelAdapter = new NumericWheelAdapter(context, 0, 59);
            minuteNumericWheelAdapter.setLabel("");
            wl_min.setViewAdapter(minuteNumericWheelAdapter);
            List<String> asList2 = Arrays.asList(minute_start);
            int min_index = asList2.indexOf(TimeKit.getMinuteStr(currentTime));
            wl_min.setCurrentItem(min_index);
        }
    }

    /**
     * 同步现在之后的分钟
     * @param context
     * @param currentHour 是不是当前小时
     */
    void synchronousMinute(Activity context, boolean currentHour) {
        long currentTime = System.currentTimeMillis();
        if (currentHour) {
            NumericWheelAdapter minuteNumericWheelAdapter = new NumericWheelAdapter(context, TimeKit.getMinute(currentTime), 59);
            minuteNumericWheelAdapter.setLabel("");
            wl_min.setViewAdapter(minuteNumericWheelAdapter);
            wl_min.setCurrentItem(0);
        } else {
            NumericWheelAdapter minuteNumericWheelAdapter = new NumericWheelAdapter(context, 0, 59);
            minuteNumericWheelAdapter.setLabel("");
            wl_min.setViewAdapter(minuteNumericWheelAdapter);
            List<String> asList2 = Arrays.asList(minute_start);
            int min_index = asList2.indexOf(TimeKit.getMinuteStr(currentTime));
            wl_min.setCurrentItem(min_index);
        }
    }

    /**
     * 点击事件
     */
    public interface Listener {
        void click(@Nullable String time);
    }
}
