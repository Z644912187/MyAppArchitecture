package com.sjtu.charles.wallet.mvp;

import com.sjtu.charles.core.mvp.BasePresenter;
import com.sjtu.charles.core.mvp.BaseView;

/**
 * Created by Charles on 2016/5/18.
 */
public class WalletContract {
    public interface View extends BaseView<Presenter> {
        /**
         * 网络错误
         */
        void onErrorNetWork();

        /**
         * 充值成功
         */
        void rechargeSuccessful();

        /**
         * 充值失败
         */
        void rechargeFailed();

        /**
         * 提现成功
         */
        void withdrawSuccessful();

        /**
         * 提现失败
         */
        void withdrawFailed();

    }

    public interface Presenter extends BasePresenter {
        /**
         * 充值钱包
         * * @param accout 充值数字
         */
        void rechargeWallet(long accout);

        /**
         * 提现
         * @param accout 提现数字
         */
        void withdrawDeposit(long accout);
    }
}
