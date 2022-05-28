package com.comm.util.openlib.rx;

public interface ObserverM<T> {
    void onNext(T t);
    void onComplete();
}
