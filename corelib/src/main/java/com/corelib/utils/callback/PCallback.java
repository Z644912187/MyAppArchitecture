package com.corelib.utils.callback;

/**
 * Created by charles on 2015/5/5.
 */
public interface PCallback<T, K> {
    void onSuccess(T res);

    void onError(K error);

    void onProgress(int progress);
}
