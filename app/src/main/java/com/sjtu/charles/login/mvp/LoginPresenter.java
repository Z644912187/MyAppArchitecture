package com.sjtu.charles.login.mvp;

import android.content.Context;

/**
 * Created by charles on 2016/5/22.
 */
public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";
    private Context mContext;
    private LoginContract.View mView;
    public LoginPresenter(Context context, LoginContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void setLoginView(LoginContract.View loginView) {
        mView = loginView;
        mView.setPresenter(this);
    }

    @Override
    public void switchToRegisterView() {
        mView.showRegisterView(true);
    }

    @Override
    public void switchToLoginView() {
        mView.showLoginView(true);
    }
}
