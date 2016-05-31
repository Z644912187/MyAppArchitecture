package com.corelib.utils.callback;

/**
 * Created by charles on 2015/6/10.
 */
public interface ECallback<T, K> {
    void onSuccess(T res);

    void onError(K error);

    void onThrowable(Throwable e);
}
