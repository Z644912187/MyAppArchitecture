package com.sjtu.charles.rxactivitydemo;

import com.sjtu.charles.core.rx.ApiCallback;
import com.sjtu.charles.core.rx.BridgeContractBridge;
import com.sjtu.charles.core.rx.SubscriberCallBack;
import com.sjtu.charles.login.http.response.LolLoginResponse;

/**
 * Created by zhuyifei
 * on 2016/6/20.
 */
public class DemoPresenter extends BridgeContractBridge<DemoContract.DemoView> implements DemoContract.DemoPresenter {

    public DemoPresenter(DemoContract.DemoView view) {
        attachView(view);
    }


    @Override
    public void loadData(String userName, String password) {
        mvpView.showLoading();
        addSubscription(apiStores.lolLogin(userName, password),
                new SubscriberCallBack<>(new ApiCallback<LolLoginResponse>() {
                    @Override
                    public void onSuccess(LolLoginResponse model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                }));
    }
}
