package com.sjtu.charles.login.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.corelib.animations.fragmentanimations.CubeAnimation;
import com.corelib.base.ui.TFragment;
import com.sjtu.charles.R;
import com.sjtu.charles.core.AppConstants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by charles on 2016/5/22.
 */
public class LoginFragment extends TFragment implements LoginContract.View {

    private static final String TAG= "LoginFragment";
    private static final int ANIMATION_DURATION = 500;

    private boolean mIsRegisterView = false;

    private LoginContract.Presenter mPresenter;

    //Views
    @Bind(R.id.layout_login)
    public ViewGroup mLoginLayout;
    @Bind(R.id.layout_register)
    public ViewGroup mRegisterLayout;

    public static LoginFragment newInstance(String action, boolean withAnimation, int direction) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle arguments = new Bundle();
        arguments.putString("action", action);
        arguments.putBoolean("withAnimation", withAnimation);
        arguments.putInt("direction", direction);
        loginFragment.setArguments(arguments);
        return loginFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        String action = null;
        View view = null;
        if (arguments != null) {
            action = arguments.getString("action", null);
        }
        view = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, view);
        if (AppConstants.ACTION_REGISTER.equals(action)) {
            mIsRegisterView = true;
            mRegisterLayout.setVisibility(View.VISIBLE);
        } else {
            mIsRegisterView = false;
            mLoginLayout.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoginView(boolean withAnimation) {
        getArguments().putInt("direction", CubeAnimation.RIGHT);
        getArguments().putBoolean("withAnimation", withAnimation);
        LoginFragment loginFragment = LoginFragment.newInstance(AppConstants.ACTION_LOGIN, withAnimation,
                CubeAnimation.RIGHT);
        mPresenter.setLoginView(loginFragment);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.flayout_login, loginFragment);
        ft.commit();
    }

    @Override
    public void showRegisterView(boolean withAnimation) {
        getArguments().putInt("direction", CubeAnimation.LEFT);
        getArguments().putBoolean("withAnimation", withAnimation);
        LoginFragment loginFragment = LoginFragment.newInstance(AppConstants.ACTION_REGISTER, withAnimation,
                CubeAnimation.LEFT);
        mPresenter.setLoginView(loginFragment);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.flayout_login, loginFragment);
        ft.commit();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        boolean withAnimation = false;
        int direction = CubeAnimation.LEFT;
        Bundle arguments = getArguments();
        if (arguments != null) {
            withAnimation = arguments.getBoolean("withAnimation", false);
            direction = arguments.getInt("direction", CubeAnimation.LEFT);
        }
        if (withAnimation) {
            switch (direction) {
                case CubeAnimation.RIGHT:
                    return CubeAnimation.create(CubeAnimation.RIGHT, enter, ANIMATION_DURATION);
                default:
                    return CubeAnimation.create(CubeAnimation.LEFT, enter, ANIMATION_DURATION);
            }
        } else {
            return null;
        }
    }

    @OnClick(R.id.btn_switch_to_login)
    public void onSwitchToLoginClicked(Button button) {
        mPresenter.switchToLoginView();
    }

    @OnClick(R.id.btn_switch_to_register)
    public void onSwitchToRegisterClicked(Button button) {
        mPresenter.switchToRegisterView();
    }
}
