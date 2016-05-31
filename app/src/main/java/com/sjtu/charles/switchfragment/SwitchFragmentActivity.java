package com.sjtu.charles.switchfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.corelib.animations.fragmentanimations.CubeAnimation;
import com.corelib.base.ui.TActivity;
import com.sjtu.charles.R;
import com.sjtu.charles.core.AppConstants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SwitchFragmentActivity extends TActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_fragment);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        String action = null;
        if (intent != null) {
            action = intent.getAction();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, SwitchFragment1.newInstance(action, false, -1))
                .commitAllowingStateLoss();

    }

    @OnClick(R.id.btn_switch_to_2)
    public void onSwitchTo2Clicked(Button button) {
        SwitchFragment2 loginFragment = SwitchFragment2.newInstance(AppConstants.ACTION_REGISTER, true,
                CubeAnimation.LEFT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, loginFragment)
                .commitAllowingStateLoss();
    }


    @OnClick(R.id.btn_switch_to_1)
    public void onSwitchTo1Clicked(Button button) {
        SwitchFragment1 loginFragment = SwitchFragment1.newInstance(AppConstants.ACTION_REGISTER, true,
                CubeAnimation.LEFT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, loginFragment)
                .commitAllowingStateLoss();
    }

}
