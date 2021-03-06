package com.sjtu.charles.switchfragment;

import android.app.Activity;
import android.content.Intent;
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
import com.sjtu.charles.main.mvp.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by charles on 2016/5/22.
 */
public class SwitchFragment1 extends TFragment {

    private static final int ANIMATION_DURATION = 500;

    //Views
    @Bind(R.id.layout_switch_to_1)
    public ViewGroup mLoginLayout;

    public static SwitchFragment1 newInstance(String action, boolean withAnimation, int direction) {
        SwitchFragment1 loginFragment = new SwitchFragment1();
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
        View view = inflater.inflate(R.layout.fragment_switch1, null);
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
                    return CubeAnimation.create(CubeAnimation.RIGHT, enter, ANIMATION_DURATION);
                default:
                    return CubeAnimation.create(CubeAnimation.LEFT, enter, ANIMATION_DURATION);
            }
        } else {
            return null;
        }
    }

    @OnClick(R.id.btn_goin)
    public void onbtn_goin1Clicked(Button button) {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }
}
