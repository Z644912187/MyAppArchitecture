package com.corelib.log;


import org.apache.log4j.Level;

/**
 * Created by wulei on 15/8/21.
 * <p/>
 * 日志配置文件
 */
public class LogConfigure {
    private static final String TAG = "LogConfigure";

    /**
     * 控制台日志打印开关，主要是弥补log4j控制台堆栈信息打印的不足
     */
    public static final boolean TOGGLE_CONSOLE = true;

    /**
     * 文件日志配置
     */
    public static void init(String cachePath) {
        try {
            final LogConfigurator logConfigurator = new LogConfigurator();
            logConfigurator.setFileName(cachePath);

            LogUtil.info(TAG, "log path: " + cachePath);

            // 日志的默认打印级别，会打印该级别及以上级别的日志
//            logConfigurator.setRootLevel(Level.ERROR);
            logConfigurator.setRootLevel(Level.INFO);
            logConfigurator.setLevel("org.apache", Level.ALL);
            logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
            // 5M 开始分文件
            logConfigurator.setMaxFileSize(1024 * 1024 * 5);
            logConfigurator.setImmediateFlush(true);
            logConfigurator.configure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 本地日志清理
     */
    public static void clean() {
        //TODO
    }
}
