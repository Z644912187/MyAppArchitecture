package com.sjtu.charles.main.mvp;

import com.sjtu.charles.core.mvp.BasePresenter;
import com.sjtu.charles.core.mvp.BaseView;

/**
 * Created by charles
 * Data: 2016/5/6.
 */
public class MainContract {

    public interface View extends BaseView<Present> {

    }

    public interface Present extends BasePresenter {
        void lolToken();
    }

}
