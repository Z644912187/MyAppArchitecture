package com.corelib.utils.callback;

/**
 * Created by charles on 2015/5/1.
 */
public interface Callback<T, K> {
    void onSuccess(T res);

    void onError(K error);
}
