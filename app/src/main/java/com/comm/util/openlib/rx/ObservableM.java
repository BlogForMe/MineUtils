package com.comm.util.openlib.rx;

public abstract class ObservableM<T> {
    public static <T> ObservableM<T> create(ObservableM<T> observable){
        return  observable;
    }

    public abstract void  subscribe(ObserverM<T> observer);
}
