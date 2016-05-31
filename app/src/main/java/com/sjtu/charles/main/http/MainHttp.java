package com.sjtu.charles.main.http;

import com.sjtu.charles.core.http.RetrofitManager;
import com.sjtu.charles.core.http.constants.HttpConstants;
import com.sjtu.charles.login.http.response.LolLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by charles
 * Data: 2016/5/6.
 */
public class MainHttp {
    private MainHttp() {

    }

    private static class InstanceHolder {
        private static final MainHttp instance = new MainHttp();
    }

    public static MainHttp getInstance() {
        return InstanceHolder.instance;
    }

    public void getLolToken(Callback<LolLoginResponse> callback) {
        RetrofitManager manager =  RetrofitManager.getInstance();
        Call<LolLoginResponse> call = manager.create().lolLogin(HttpConstants.LOL_ACCOUNT, HttpConstants.LOL_PASSWORD);
        manager.enqueue(call, callback);
    }

}
