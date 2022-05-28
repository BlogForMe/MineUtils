package com.comm.util.openlib.rxretrofit.xiangx.core.mapop;

import com.comm.util.openlib.rxretrofit.xiangx.core.ObservableSource;
import com.comm.util.openlib.rxretrofit.xiangx.core.Observer;

public class ObservableMap<T,U> extends  AbstractObservableWithUpStream<T,U> {

    final Function<T,U> function;
    public ObservableMap(ObservableSource source,Function<T,U> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        source.subscribeObserver(new MapObserver(observer,function));
    }

    /**
     * 持有下游的引用
     * @param <T>
     * @param <U>
     */
    static final class MapObserver<T,U> extends  BasicFuseableObserver<T,U>{

        final Function<T,U> mapper;
        public MapObserver(Observer<U> actual, Function<T,U> mapper) {
            super(actual);
            this.mapper = mapper;
        }

        @Override
        public void onNext(T t) {
            U apply = mapper.apply(t); //数据变换， 如何变由用户实现
            downStream.onNext(apply); //持有下游的observer,向下游传递数据
        }
    }


}
