package com.comm.util.openlib.rxretrofit.xiangx.core;

/**
 * 绑定发射器 被观察者和发射器分离了
 * @param <T>
 */
public interface ObservableOnSubscribe<T> {
    void subscribe(Emitter<T> emitter);
}
