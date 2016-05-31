package com.corelib.view.scroll;

import android.support.v4.widget.NestedScrollView;

import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class OverScrollDecoratorExtHelper {
    public static IOverScrollDecor setUpOverNestedScroll(NestedScrollView scrollView) {
        return new VerticalOverScrollBounceEffectDecorator(new NestedScrollViewOverScrollDecorAdapter(scrollView));
    }
}
