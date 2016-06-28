package com.corelib.log;

import org.apache.log4j.Logger;

/**
 * Created by wulei on 15/12/4.
 * <p/>
 * 日志工具类 薄封装
 *
 * 日志级别
 * ALL<DEBUG<INFO<WARN<ERROR<FATAL<OFF
 */
public class Log {
    /**
     * 记录详细的重要信息
     *
     * @param tag
     * @param message
     */
    public static void d(String tag, String message) {
        Logger.getLogger(tag).debug(message);
    }

    /**
     * 记录简略的重要信息
     *
     * @param tag
     * @param message
     */
    public static void i(String tag, String message) {
        Logger.getLogger(tag).info(message);
    }

    /**
     * 记录网络返回的json
     *
     * @param tag
     * @param message
     */
//    public static void i(String tag, Object message) {
//        i(tag, new Gson().toJson(message));
//    }

    /**
     * 记录异常数据信息，如无数据，服务器连接超时
     *
     * @param tag
     * @param message
     */
    public static void w(String tag, String message) {
        Logger.getLogger(tag).warn(message);
    }

    /**
     * 记录严重的异常
     *
     * @param tag
     * @param message
     */
    public static void e(String tag, String message) {
        Logger.getLogger(tag).error(message);
    }

    /**
     * 记录严重的异常,如exception
     *
     * message 为“”可能导致日志丢失
     *
     * @param tag
     * @param message
     */
    public static void e(String tag, String message, Throwable tr) {
        Logger.getLogger(tag).error(message, tr);
    }

    /**
     * 系统崩溃日志
     *
     * @param tag
     * @param message
     */
    public static void f(String tag, String message) {
        Logger.getLogger(tag).fatal(message);
    }

    /**
     * 系统崩溃日志
     * @param tag
     * @param message
     * @param tr  崩溃异常堆栈
     */
    public static void f(String tag, String message, Throwable tr) {
        Logger.getLogger(tag).fatal(message, tr);
    }
}
