package com.sjtu.charles.playmate.mvp;

import android.app.Activity;
import android.content.Context;

import com.sjtu.charles.playmate.http.MakePlaymateHttp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by charles on 2016/5/23.
 */
public class MakePlaymatePresenter implements MakePlaymateContract.Presenter {
    private MakePlaymateContract.View mView;
    private Context context;

    public MakePlaymatePresenter(MakePlaymateContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void requestMakePlaymate() {
        MakePlaymateHttp.getInstance().requestMakePlaymate(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                // TODO: 需要先做一些解析再调用
                mView.makePlaymateComplete();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    @Override
    public void pickPhoto() {
        // TODO: 挑选照片,并对返回值进行处理
    }

    @Override
    public void getGameName() {

    }

    @Override
    public void cancelMakePlaymate() {
        ((Activity)context).finish();
    }
}
