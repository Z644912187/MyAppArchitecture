package com.sjtu.charles.core.http.interceptor;

import com.corelib.log.Log;

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

        Log.i(TAG, "----        Request       ---- " + "\n" + request.toString());
        Log.i(TAG, "----        Response      ---- " + "\n" + response.toString());

        return response;
    }
}
