package com.sjtu.charles.core;

import android.app.Application;

import com.corelib.log.LogConfigure;
import com.corelib.log.LogUtil;
import com.sjtu.charles.core.cache.CachePath;


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

        //初始化日志
        LogConfigure.init(CachePath.LOG);

        recordUncaughtException();

        initModules();

        LogUtil.info(TAG, "app startup....");
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

    /**
     * 记录uncaughtException日志, 打印堆栈信息
     */
    private void recordUncaughtException() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                LogUtil.fatal(TAG, "UncaughtException", ex);
            }
        });
    }
}
