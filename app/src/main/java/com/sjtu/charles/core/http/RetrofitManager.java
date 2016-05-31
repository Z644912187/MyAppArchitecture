package com.sjtu.charles.core.http;

import android.content.Context;
import android.widget.Toast;

import com.corelib.utils.net.Network;
import com.sjtu.charles.R;
import com.sjtu.charles.core.MyApplication;
import com.sjtu.charles.core.cache.CachePath;
import com.sjtu.charles.core.http.constants.HttpConstants;
import com.sjtu.charles.core.http.converter.gson.GsonConverterFactory;
import com.sjtu.charles.core.http.interceptor.CoreInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by charles
 * Data: 2016/3/30.
 */
public class RetrofitManager {
    private Context mContext = MyApplication.getInstance();
    // 网络请求超时
    private static final int TIME_OUT = 5 * 1000;
    private static final int CACHE_SIZE = 20 * 1024 * 1024;

    private RetrofitManager() {
    }

    private static class InstanceHolder {
        private static final RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * 获取一个服务对象，使用Gson转换器，用于json数据的交互
     *
     * @return
     */
    public APIService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConstants.getEndPoint(HttpConstants.env))
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();

        return retrofit.create(APIService.class);
    }

    /**
     * 获取一个服务对象，传入一个新的转换器，处理 流，原始的json字符串
     *
     * @param factory
     * @return
     */
    public APIService create(Converter.Factory factory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConstants.getEndPoint(HttpConstants.env))
                .addConverterFactory(factory)
                .client(getClient())
                .build();

        return retrofit.create(APIService.class);
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
        if (!Network.isConnected(MyApplication.getInstance())) {
            Toast.makeText(mContext, mContext.getString(R.string.toast_check_net), Toast.LENGTH_SHORT).show();
        }
        call.enqueue(callback);
    }
}
