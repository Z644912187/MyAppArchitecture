package com.sjtu.charles.switchfragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

        addToBackStackFragment(R.id.container, SwitchFragment1.newInstance(action, false, -1));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getFragments().size() > 1) {
                    //回退到Fragment1
                    //将当前的事务退出回退栈
                    getSupportFragmentManager().popBackStack();
                } else {
                    onBackPressed();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_switch_to_2)
    public void onSwitchTo2Clicked(Button button) {
        SwitchFragment2 loginFragment = SwitchFragment2.newInstance(AppConstants.ACTION_REGISTER, true,
                CubeAnimation.LEFT);

        addToBackStackFragment(R.id.container, loginFragment);
    }


    @OnClick(R.id.btn_switch_to_1)
    public void onSwitchTo1Clicked(Button button) {
        SwitchFragment1 loginFragment = SwitchFragment1.newInstance(AppConstants.ACTION_REGISTER, true,
                CubeAnimation.LEFT);
        addToBackStackFragment(R.id.container, loginFragment);
    }

}
