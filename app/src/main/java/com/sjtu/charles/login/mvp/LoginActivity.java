package com.sjtu.charles.login.mvp;

import android.content.Intent;
import android.os.Bundle;

import com.corelib.base.ui.TActivity;
import com.sjtu.charles.R;

/**
 * Created by charles on 2016/5/22.
 */
public class LoginActivity extends TActivity {

    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.black);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String action = null;
        if (intent != null) {
            action = intent.getAction();
        }
        LoginFragment loginFragment = LoginFragment.newInstance(action, false, -1);
        mPresenter = new LoginPresenter(this, loginFragment);
        replaceFragment(R.id.flayout_login, loginFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
