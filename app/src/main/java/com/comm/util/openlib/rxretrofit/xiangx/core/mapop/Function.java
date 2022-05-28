package com.comm.util.openlib.rxretrofit.xiangx.core.mapop;

/**
 * 事件变换
 * @param <T>
 * @param <R>
 */
public interface Function<T,R> {
    R apply(T t);
}
