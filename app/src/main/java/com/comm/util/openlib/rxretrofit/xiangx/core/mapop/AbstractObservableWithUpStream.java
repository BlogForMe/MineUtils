package com.comm.util.openlib.rxretrofit.xiangx.core.mapop;

import com.comm.util.openlib.rxretrofit.xiangx.core.Observable;
import com.comm.util.openlib.rxretrofit.xiangx.core.ObservableSource;

//装饰器类
public abstract class AbstractObservableWithUpStream<T,U> extends Observable {
    protected final ObservableSource source;

    public AbstractObservableWithUpStream(ObservableSource source) {
        this.source = source;
    }
}
