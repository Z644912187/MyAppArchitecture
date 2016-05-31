package com.sjtu.charles.main.mvp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corelib.base.ui.TFragment;
import com.sjtu.charles.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeTeamFragment extends TFragment {


    public MakeTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_team, container, false);
    }

}
