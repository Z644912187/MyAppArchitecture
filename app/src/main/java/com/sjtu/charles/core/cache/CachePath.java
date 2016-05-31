package com.sjtu.charles.core.cache;

import android.os.Environment;

/**
 * Created by charles on 15/8/21.
 *
 * 配置本地目录信息
 */
public class CachePath {
    /**
     * SD卡目录
     */
    public final static String SD = Environment.getExternalStorageDirectory().getPath();

    /**
     * 缓存地址目录
     */
    public final static String TEMP = SD + "/" + AppInfo.getPackageName();

    /**
     * 日志文件地址
     */
    public final static String LOG = TEMP + "/log" + "/log.txt";

    /**
     * 下载文件保存目录
     */
    public final static String DOWNLOAD = TEMP + "/download";

    /**
     * 下载新版本
     */
    public final static String DOWNLOAD_NEW_APK = DOWNLOAD + "/zhaoshangbao.apk";

    /**
     * 网络请求缓存目录
     */
    public final static String HTTPCACHE = TEMP + "/httpCache";
}
