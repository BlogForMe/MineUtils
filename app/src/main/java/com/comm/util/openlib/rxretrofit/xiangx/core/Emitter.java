package com.comm.util.openlib.rxretrofit.xiangx.core;


/**
 *  给用户发消息用的接口
 * @param <T>
 */
public interface Emitter<T> {
    void onNext(T t);

    void onError(Throwable e);

    void onComplete();
}
