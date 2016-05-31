package com.sjtu.charles.playmate.mvp;

import android.os.Bundle;
import android.view.View;

import com.corelib.base.ui.TActivity;
import com.sjtu.charles.R;

public class MakePlaymateActivity extends TActivity implements MakePlaymateContract.View,View.OnClickListener {

    private MakePlaymatePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_playmate);
        initParams();
    }

    private void initParams() {
        mPresenter=new MakePlaymatePresenter(this,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    @Override
    public void makePlaymateComplete() {

    }
    
    @Override
    public void makePlaymateFailed()
    {
        
    }
    
    @Override
    public void pickPhotoSuccess() {

    }
}
