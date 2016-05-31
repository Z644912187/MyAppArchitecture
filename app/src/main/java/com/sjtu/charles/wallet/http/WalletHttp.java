package com.sjtu.charles.wallet.http;


import retrofit2.Callback;

/**
 * 钱包相关接口
 * Created by Charles on 2016/5/22.
 */
public class WalletHttp {
    private WalletHttp() {

    }

    private static class InstanceHolder {
        private static final WalletHttp instance = new WalletHttp();
    }

    public static WalletHttp getInstance() {
        return InstanceHolder.instance;
    }

    /**
     *
     * @param callback  注意这里的Object替换成自己定义的json bean
     * @param accout  充值金额
     */
    public void rechargeWallet(Callback<Object> callback, long accout) {

    }

    public void withdrawDeposit(Callback<Object> callback, long accout) {

    }
}
