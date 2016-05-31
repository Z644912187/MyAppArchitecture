package com.sjtu.charles.switchfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.corelib.base.ui.TActivity;
import com.sjtu.charles.R;

public class SwitchFragmentActivity extends TActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

}
