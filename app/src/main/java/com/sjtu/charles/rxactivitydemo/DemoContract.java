package com.sjtu.charles.rxactivitydemo;

import com.sjtu.charles.login.http.response.LolLoginResponse;

/**
 * Created by zhuyifei on 2016/6/20.
 */
public class DemoContract {

    public interface DemoView {

        void getDataSuccess(LolLoginResponse model);

        void getDataFail(String msg);

        void showLoading();

        void hideLoading();
    }

    public interface DemoPresenter {
        void loadData(String userName,String password);
    }
}
