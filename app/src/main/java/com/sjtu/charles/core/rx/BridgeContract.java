package com.sjtu.charles.core.rx;

public interface BridgeContract<V> {

    void attachView(V view);

    void detachView();

}
