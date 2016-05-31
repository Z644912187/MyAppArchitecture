package com.sjtu.charles.switchfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.corelib.animations.fragmentanimations.CubeAnimation;
import com.corelib.animations.fragmentanimations.FlipAnimation;
import com.corelib.animations.fragmentanimations.SidesAnimation;
import com.corelib.base.ui.TFragment;
import com.sjtu.charles.R;
import com.sjtu.charles.core.AppConstants;
import com.sjtu.charles.login.mvp.LoginContract;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by charles on 2016/5/22.
 */
public class SwitchFragment2 extends TFragment {

    private static final int ANIMATION_DURATION = 500;

    //Views
    @Bind(R.id.layout_switch_to_2)
    public ViewGroup mLoginLayout;

    public static SwitchFragment2 newInstance(String action, boolean withAnimation, int direction) {
        SwitchFragment2 loginFragment = new SwitchFragment2();
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
        View view = inflater.inflate(R.layout.fragment_switch2, null);
        ButterKnife.bind(this, view);
        mLoginLayout.setVisibility(View.VISIBLE);
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
                    return SidesAnimation.create(SidesAnimation.RIGHT, enter, ANIMATION_DURATION);
                default:
                    return SidesAnimation.create(SidesAnimation.LEFT, enter, ANIMATION_DURATION);
            }
        } else {
            return null;
        }
    }

}
