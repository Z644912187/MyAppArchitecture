package com.sjtu.charles.core.rx;

import android.content.Context;

import com.sjtu.charles.core.cache.CachePath;
import com.sjtu.charles.core.http.constants.HttpConstants;
import com.sjtu.charles.core.http.interceptor.CoreInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhuyifei on 2016/6/20.
 */
public class RxRetrofitManager  {

    // 网络请求超时
    private static final int TIME_OUT = 5 * 1000;
    private static final int CACHE_SIZE = 20 * 1024 * 1024;

    private RxRetrofitManager() {
    }

    private static class InstanceHolder {
        private static final RxRetrofitManager instance = new RxRetrofitManager();
    }

    public static RxRetrofitManager getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * 获取一个服务对象，使用Gson转换器，用于json数据的交互
     *
     * @return
     */
    public RxApiService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConstants.getEndPoint(HttpConstants.env))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getClient())
                .build();

        return retrofit.create(RxApiService.class);
    }


    /**
     * 获取一个带拦截器的client对象
     * @return
     */
    private OkHttpClient getClient(){
        //设置缓存目录
        File dir = new File(CachePath.HTTPCACHE);
        Cache cache = new Cache(dir, CACHE_SIZE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new CoreInterceptor())
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build();
        return client;
    }

    /**
     * 执行
     * @param call
     * @param callback
     */
    public void enqueue(Call call, Callback callback) {
        call.enqueue(callback);
    }

}
