package com.sjtu.charles.core.http.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by charles
 * Data: 2016/4/10.
 */
public class HttpUtils {
    /**
     * 由于服务端返回的json格式问题，将retrofit返回的jsonBean变成我们需要的jsonBean
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T formatJsonBean(Object json, Class<T> classOfT) throws JsonSyntaxException {
        Gson gson = new Gson();
        T result = gson.fromJson(gson.toJson(json), classOfT);
        return result;
    }
}
