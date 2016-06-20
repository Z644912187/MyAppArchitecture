package com.sjtu.charles.core.rx;


/**
 * Created by zhuyifei on 16/6/20.
 */
public interface ApiCallback<T> {

    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();

}
