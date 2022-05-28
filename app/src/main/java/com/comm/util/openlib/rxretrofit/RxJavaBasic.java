package com.comm.util.openlib.rxretrofit;

import android.util.Log;
import com.android.util.AppUtil;
import com.comm.util.openlib.rx.ObservableM;
import com.comm.util.openlib.rx.ObserverM;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RxJavaBasic {
    public  static   String TAG = AppUtil.getApp().getClass().getSimpleName();

    public static void mineObserver(){
        ObservableM.create(new ObservableM<String>() {

            @Override
            public void subscribe(ObserverM<String> observer) {
                observer.onNext("hello");
                observer.onNext("world");
                observer.onComplete();
            }
        }).subscribe(new ObserverM<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("onNext  " + s);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete  ");
            }
        });
    }

    public static void just(){
        Observable.just(1,2,3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "=================onSubscribe");
                    }
                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "=================onNext " + integer);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "=================onError ");
                    }
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "=================onComplete ");
                    }
                });
    }

    void test(){
        Observer<Integer> observer  = new Observer<Integer>(){

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

    }


}
