package com.sjtu.charles.wallet.mvp;

import android.os.Bundle;
import android.view.View;

import com.corelib.base.ui.TActivity;
import com.sjtu.charles.R;

/**
 * 钱包界面 ， 不熟悉MVP小伙伴们可以参考这部分
 * Created by Charles on 2016/5/18.
 */
public class WalletActivity extends TActivity implements View.OnClickListener, WalletContract.View {

    private WalletContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        mPresenter = new WalletPresenter(this,this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (true/*id == R.id.xxx*/) { //点击充值
            mPresenter.rechargeWallet(1000);
        } else if (true/*id == R.id.yyy*/) {  //提现
            mPresenter.withdrawDeposit(1000);
        }

    }

    @Override
    public void onErrorNetWork() {
        showToast(R.string.toast_check_net);
    }

    @Override
    public void rechargeSuccessful() {

    }

    @Override
    public void rechargeFailed() {

    }

    @Override
    public void withdrawSuccessful() {

    }

    @Override
    public void withdrawFailed() {

    }

}
