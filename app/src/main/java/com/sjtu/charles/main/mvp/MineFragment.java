package com.sjtu.charles.main.mvp;


import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corelib.base.ui.TFragment;
import com.corelib.view.scroll.OverScrollDecoratorExtHelper;
import com.sjtu.charles.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 个人中心
 */
public class MineFragment extends TFragment {

    @Bind(R.id.scroll_mine)
    NestedScrollView scroll_mine;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);

        OverScrollDecoratorExtHelper.setUpOverNestedScroll(scroll_mine);

        return view;
    }

}
