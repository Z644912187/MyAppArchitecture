package com.sjtu.charles.core;

import android.app.Application;

import com.corelib.crash.AndroidCrash;
import com.corelib.crash.HttpReportCallback;
import com.corelib.log.LogConfigure;
import com.corelib.log.Log;
import com.sjtu.charles.core.cache.CachePath;

import java.io.File;


//import com.squareup.leakcanary.LeakCanary;
//import com.squareup.leakcanary.RefWatcher;

/**
 * Created by charles on 2016/3/28.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static MyApplication mInstance;
    //umeng 推送


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        initLog();

        initModules();

        Log.i(TAG, "app startup....");
//        LeakCanary.install(this);
    }


    /**
     * 初始化各种模块的代码。。。地图，友盟。。。。。
     */
    private void initModules() {

    }

    /**
     * 提供全局的Context
     *
     * @return
     */
    public static Application getInstance() {
        return mInstance;
    }


    private void initLog() {

        //初始化日志
        LogConfigure.init(CachePath.LOG);

        /**
         * 记录uncaughtException日志
         */
        AndroidCrash.getInstance().setCrashReporter(new HttpReportCallback() {
            @Override
            public void uploadException2remote(File file) {
                //TODO 可以直接上传文件
            }
        }).init(this,CachePath.LOG_CRASH);
    }
}
