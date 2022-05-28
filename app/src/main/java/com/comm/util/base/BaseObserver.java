package com.comm.util.base;


import io.reactivex.observers.ResourceObserver;

/**
 * Created by A on 2018/2/3.
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    public void onFinish() {

    }

    @Override
    public void onError(Throwable e) {
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
