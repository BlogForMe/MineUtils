package com.comm.util.openlib.rxretrofit.retrofit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxManager {
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public  void register(Disposable d){
        mCompositeDisposable.add(d);
    }

    public  void unSubscribe(){
        mCompositeDisposable.dispose();
    }
}
