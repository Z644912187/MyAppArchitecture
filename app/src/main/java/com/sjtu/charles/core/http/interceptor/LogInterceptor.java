package com.sjtu.charles.core.http.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by charles
 * Name:黄浦
 * Email:hpdroid@yahoo.com
 * Date: 16/5/19
 */
public class LogInterceptor implements Interceptor{
    private static final String TAG = LogInterceptor.class.getSimpleName();
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder();
        Request request = requestBuilder.method(originalRequest.method(), originalRequest.body()).build();
        Response response = chain.proceed(request);
        // 在这里做任何返回数据操作
        Log.e(TAG, "url: " + chain.request().url().toString());
        try {
            String result = response.body().string();
            Log.e(TAG, result);
            ResponseBody body = ResponseBody.create(response.body().contentType(), result);
            return response.newBuilder().body(body).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
