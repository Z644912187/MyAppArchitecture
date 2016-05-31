package com.sjtu.charles.core.http.interceptor;

import com.corelib.log.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by charles
 * Data: 2016/4/25.
 */
public class CoreInterceptor implements Interceptor {
    private static final String TAG = "CoreInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        LogUtil.info(TAG, "----        Request       ---- " + "\n" + request.toString());
        LogUtil.info(TAG, "----        Response      ---- " + "\n" + response.toString());

        return response;
    }
}
