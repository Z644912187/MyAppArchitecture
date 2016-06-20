package com.sjtu.charles.rxactivitydemo;

import android.os.Bundle;

import com.sjtu.charles.R;
import com.sjtu.charles.core.rx.RxTActivity;
import com.sjtu.charles.login.http.response.LolLoginResponse;

public class DemoRxActivity extends RxTActivity<DemoPresenter> implements DemoContract.DemoView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_rx);

        //TODO
        mvpPresenter.loadData("","");
    }

    @Override
    protected DemoPresenter createPresenter() {
        return new DemoPresenter(this);
    }

    @Override
    public void getDataSuccess(LolLoginResponse model) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
