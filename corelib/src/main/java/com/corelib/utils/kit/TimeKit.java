package com.corelib.utils.kit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 */
public class TimeKit {

    /**
     * 获取主页今日任务的时间
     * @return xxxx年x月x日 星期x
     */
    public static String getCurrentTaskTime() {
        StringBuilder target = new StringBuilder();
        //当前时间
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        //获取xxxx年x月x日
        target.append(String.format("%s年%s月%s日 ",
                                    now.get(Calendar.YEAR),
                                    now.get(Calendar.MONTH) + 1,
                                    now.get(Calendar.DAY_OF_MONTH)));
        //获取星期x
        switch (now.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY: {
                //星期天
                target.append("星期天");
            }
            break;
            case Calendar.MONDAY: {
                //星期一
                target.append("星期一");
            }
            break;
            case Calendar.TUESDAY: {
                //星期二
                target.append("星期二");
            }
            break;
            case Calendar.WEDNESDAY: {
                //星期三
                target.append("星期三");
            }
            break;
            case Calendar.THURSDAY: {
                //星期四
                target.append("星期四");
            }
            break;
            case Calendar.FRIDAY: {
                //星期五
                target.append("星期五");
            }
            break;
            case Calendar.SATURDAY: {
                //星期六
                target.append("星期六");
            }
            break;
        }
        return target.toString();
    }
    //HH:MM（24时制）
    public static String parseToHAndM(long time) {
        Date date = new Date(time);
        return String.format("%tR", date);
    }

    public static String parseToHAndM(String time) {
        Date date = stringToDate(time);
        if (date != null) {
            return String.format("%tR", date);
        } else {
            return "";
        }
    }

    public static String parseToFullTime(String time){
        String brith_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        brith_StrTime = sdf.format(new Date(Long.parseLong(time)));
        return brith_StrTime;
    }

    /**
     * 时间字符串转时间戳
     * @param time yyyy-MM-dd HH:mm:ss
     * @return date
     * @throws ParseException
     */
    public static Date stringToDate(String time) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //获取 年月日
    public static String getYMDTime(long time) {
        String brith_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        brith_StrTime = sdf.format(new Date(time));
        return brith_StrTime;
    }

    /**
     * 返回当天
     * @param time yyyy-MM-dd
     * @return
     */
    public static String getCurrentDay(long time) {
        Date date = new Date(time);
        return String.format("%tF", date);
    }

    /**
     * 获取给定时间的24小时制Hour
     * @param time
     * @return 11:30:30 -- 返回22
     */
    public static int get24Hour(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    /**
     * 获取给定时间的24小时制Hour
     * @param time
     * @return HH
     */
    public static String get24HourStr(long time) {
        Date date = new Date(time);
        return String.format("%tH", date);
    }
    /**
     * 获取给定时间的minute
     * @param time
     * @return 11:30:30 -- 返回22
     */
    public static int getMinute(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.MINUTE);
    }
    /**
     * 获取给定时间的minute
     * @param time
     * @return MM
     */
    public static String getMinuteStr(long time) {
        Date date = new Date(time);
        return String.format("%tM", date);
    }

}