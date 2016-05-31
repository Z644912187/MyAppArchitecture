package com.sjtu.charles.login.http;


/**
 * 注册登录相关接口
 * Created by Charles on 2016/5/22.
 */
public class LoginHttp {
    private LoginHttp() {

    }

    private static class InstanceHolder {
        private static final LoginHttp instance = new LoginHttp();
    }

    public static LoginHttp getInstance() {
        return InstanceHolder.instance;
    }


}
