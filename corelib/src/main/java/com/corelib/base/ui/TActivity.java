package com.corelib.base.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.corelib.log.LogUtil;

/**
 * Created by charles
 * Data: 2016/3/30.
 */
public class TActivity extends AppCompatActivity {
    protected String TAG;
    private static final int ANIMATION_DURATION = 1000;
    public boolean navigationIconToggle = true;
    public boolean cutActivityAnimationToggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=getLocalClassName();
        LogUtil.info(TAG,"onCreate");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode().setDuration(ANIMATION_DURATION));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.info(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.info(TAG,"onResume");
        if (getSupportActionBar() != null && navigationIconToggle) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.info(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.info(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.info(TAG,"onDestroy");
    }

    /**
     * 显示返回按钮
     */
    public void showNavigationIcon()
    {
        navigationIconToggle = true;
    }

    /**
     * 隐藏返回按钮
     */
    public void closeNavigationIcon()
    {
        navigationIconToggle = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment).commit();
    }

    @Override
    public void startActivity(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && cutActivityAnimationToggle) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else{
            super.startActivity(intent);
            overridePendingTransition(0,0);
        }
        closeCutActivityAnimation();
    }

    public View mProgressView;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show, final View view) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressView != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                        view.setVisibility(show ? View.GONE : View.VISIBLE);
                        view.animate().setDuration(shortAnimTime).alpha(
                                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                view.setVisibility(show ? View.GONE : View.VISIBLE);
                            }
                        });

                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                        mProgressView.animate().setDuration(shortAnimTime).alpha(
                                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                            }
                        });
                    } else {
                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                        view.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                }
            }
        });
    }

    /**
     * 5.0以上使用activity切换动画
     */
    public void openCutActivityAnimation()
    {
        cutActivityAnimationToggle = true;
    }

    /**
     * 5.0以上不使用activity切换动画
     */
    public void closeCutActivityAnimation()
    {
        cutActivityAnimationToggle = false;
    }

    /**
     * 隐藏软键盘输入
     */
    public void hideSoftKeyboard() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (manager != null && getCurrentFocus() != null) {
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    /**
     * 显示输入键盘
     */
    public void showSoftKeyboard(View view) {
        if (view != null) {
            view.requestFocus();
            InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
            if (manager != null) {
                manager.showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        }
    }
    /**
     * 显示TOAST
     */
    public void showToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    /**
     * 显示TOAST
     */
    public void showToast(final int resId) {
        if (resId > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
