package com.sjtu.charles.main.mvp;

import com.corelib.log.Log;
import com.sjtu.charles.login.http.response.LolLoginResponse;
import com.sjtu.charles.main.http.MainHttp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by charles
 * Data: 2016/5/6.
 */
public class MainPresenter implements MainContract.Present {
    private static final String TAG = "MainPresenter";

    @Override
    public void lolToken() {
        MainHttp.getInstance().getLolToken(new Callback<LolLoginResponse>() {
            @Override
            public void onResponse(Call<LolLoginResponse> call, Response<LolLoginResponse> response) {
                if (response.isSuccessful()) {
                    LolLoginResponse lolLoginResponse = response.body();
                    Log.e(TAG, lolLoginResponse.toString());
                }
                Log.d(TAG,response.toString());
            }

            @Override
            public void onFailure(Call<LolLoginResponse> call, Throwable t) {
                Log.f(TAG,"onFailure",t);
            }
        });
    }
}
