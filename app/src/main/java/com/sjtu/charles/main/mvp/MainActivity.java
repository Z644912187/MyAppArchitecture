package com.sjtu.charles.main.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.corelib.base.ui.TActivity;
import com.corelib.view.bottombar.BottomBarTab;
import com.corelib.view.bottombar.BottomNavigationBar;
import com.sjtu.charles.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends TActivity implements MainContract.View {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.bottom_bar)
    BottomNavigationBar bottomBar;
    private static final int TAB_ONE = 0;
    private static final int TAB_TWO = 1;
    private static final int TAB_THREE = 2;
    private static final int TAB_FOUR = 3;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_logo);
        setSupportActionBar(toolbar);

        initView();
        initParams();
        initAction();

    }

    private void initAction() {
        checkTab(TAB_ONE);
        mPresenter.lolToken();
    }

    private void initParams() {
        mPresenter = new MainPresenter();
    }

    private void initView() {
        setUpBottomNavigationBar();
    }

    /**
     * 设置底部菜单栏
     */
    public void setUpBottomNavigationBar() {
        bottomBar.setTabWidthSelectedScale(1.5f);
        bottomBar.setTextDefaultVisible(false);
        bottomBar.addTab(R.drawable.selector_swords, getString(R.string.tab_make_team), getResources().getColor(R.color.tab_one));
        bottomBar.addTab(R.drawable.selector_play_together, getString(R.string.tab_play_together), getResources().getColor(R.color.tab_two));
        bottomBar.addTab(R.drawable.selector_news, getString(R.string.tab_news), getResources().getColor(R.color.tab_three));
        bottomBar.addTab(R.drawable.selector_me, getString(R.string.tab_me), getResources().getColor(R.color.tab_four));
        bottomBar.setOnTabListener(new BottomNavigationBar.TabListener() {
            @Override
            public void onSelected(BottomBarTab tab, int position) {
                checkTab(position);
            }
        });
    }

    /**
     * 设置tab
     *
     * @param position
     */
    private void checkTab(int position) {
        Fragment fragment = null;
        switch (position) {
            case TAB_ONE:
                fragment = new MakeTeamFragment();
                break;
            case TAB_TWO:
                fragment = new MakeTeamFragment();
                break;
            case TAB_THREE:
                fragment = new MakeTeamFragment();
                break;
            case TAB_FOUR: // 我
                fragment = new MineFragment();
                break;
            default:
                fragment = new MakeTeamFragment();
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commitAllowingStateLoss();
    }
}
