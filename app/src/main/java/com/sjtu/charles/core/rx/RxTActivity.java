package com.sjtu.charles.core.rx;

import android.os.Bundle;

import com.corelib.base.ui.TActivity;

/**
 * Created by zhuyifei on 2016/6/20.
 */
public abstract class RxTActivity<P extends BridgeContractBridge> extends TActivity {

    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

}
