package com.sjtu.charles.playmate.http;

import retrofit2.Callback;

/**
 * Created by charles on 2016/5/23.
 */
public class MakePlaymateHttp {
    private MakePlaymateHttp() {

    }

    private static class InstanceHolder {
        private static final MakePlaymateHttp instance = new MakePlaymateHttp();
    }

    public static MakePlaymateHttp getInstance() {
        return InstanceHolder.instance;
    }
    public void requestMakePlaymate(Callback<Object> callback){

    }
}
