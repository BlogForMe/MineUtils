package com.comm.util.openlib.rxretrofit.xiangx.core;

/**
 * 抽象被观察者
 */
public interface ObservableSource {
    //订阅功能 绑定Observable与Observer关联
    void subscribeObserver(Observer observer);
}
