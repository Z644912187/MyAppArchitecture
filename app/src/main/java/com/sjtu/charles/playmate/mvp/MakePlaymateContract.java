package com.sjtu.charles.playmate.mvp;

import com.sjtu.charles.core.mvp.BasePresenter;
import com.sjtu.charles.core.mvp.BaseView;

/**
 * Created by charles on 2016/5/23.
 */
public class MakePlaymateContract {
    public interface View extends BaseView<Presenter>{
        /**
         * 完成陪玩的创建
         */
        void makePlaymateComplete();

        /**
         * 创建陪玩失败
         */
        void makePlaymateFailed();

        /**
         * 选取照片完成
         */
        void pickPhotoSuccess();
    }
    public interface Presenter extends BasePresenter{
        /**
         * 上传创建的陪玩信息
         */
        void requestMakePlaymate();

        /**
         * 选取本地照片
         */
        void pickPhoto();

        /**
         * 获取游戏下拉列表数据
         */
        void getGameName();

        /**
         * 取消创建,返回上一级
         */
        void cancelMakePlaymate();
    }
}
