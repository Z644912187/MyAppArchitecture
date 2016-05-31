package com.sjtu.charles.login.mvp;

import com.sjtu.charles.core.mvp.BasePresenter;
import com.sjtu.charles.core.mvp.BaseView;

/**
 * Created by charles on 2016/5/22.
 */
public class LoginContract {
    public interface View extends BaseView<Presenter> {

        /**
         * 设置Presenter
         */
        void setPresenter(LoginContract.Presenter presenter);

        /**
         * 展示登录
         */
        void showLoginView(boolean withAnimation);

        /**
         * 展示注册
         */
        void showRegisterView(boolean withAnimation);
    }

    public interface Presenter extends BasePresenter {

        /**
         * 设置LoginView
         */
        void setLoginView(LoginContract.View loginView);

        /**
         * 切换到注册View
         */
        void switchToRegisterView();

        /**
         * 切换到登录View
         */
        void switchToLoginView();
    }
}
