package com.comm.util.openlib.rxretrofit.xiangx.core.mapop;

import com.comm.util.openlib.rxretrofit.xiangx.core.Observer;

public  abstract class BasicFuseableObserver<T,U> implements Observer<T> {
    protected  final  Observer<U> downStream;

    // 参数downStream就是下游的observer
    public BasicFuseableObserver(Observer<U> downStream) {
        this.downStream = downStream;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
