package com.comm.util.openlib.rxretrofit.xiangx.core;

import com.comm.util.openlib.rxretrofit.xiangx.core.mapop.Function;
import com.comm.util.openlib.rxretrofit.xiangx.core.mapop.ObservableMap;

/**
 * 把绑定和发消息分离出来 留给子类实现
 */
public abstract class Observable<T> implements ObservableSource {
    //创建具体的被观察者
    public static  <T> Observable<T> create(ObservableOnSubscribe<T> source){
        return new ObservableCreate<>(source);
    }

    @Override
    public void subscribeObserver(Observer observer) {
        // 把这个功能留给各种不同的Observable处理
        //比如有背压等 map , flatmap...
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer observer);

    public <U> Observable map(Function<T,U> function){
        return  new ObservableMap(this,function);
    }

}
