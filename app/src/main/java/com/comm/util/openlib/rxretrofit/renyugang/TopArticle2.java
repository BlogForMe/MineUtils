package com.comm.util.openlib.rxretrofit.renyugang;

import java.util.concurrent.TimeUnit;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * https://mp.weixin.qq.com/s/elA3Gib57YGWnXOEcFOPUQ
 */
public class TopArticle2 {
    public static String TAG = "TopArticle2";

    public static void concat() {
        Observable.concat(Observable.just(1, 2),
                Observable.just(3, 4),
                Observable.just(5, 6),
                Observable.just(7, 8))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public static void concatArray() {
        Observable.concatArray(Observable.just(1, 2),
                Observable.just(3, 4),
                Observable.just(5, 6),
                Observable.just(7, 8),
                Observable.just(9, 10))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public static void merge() {
        Observable.merge(
                Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {

                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "A" + aLong;
                    }
                }),
                Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {

                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "B" + aLong;
                    }
                })
        ).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "=====================onNext " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void doOnNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "==================doOnNext " + integer);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }



    public static void  delay(){
        Observable.just(1, 2, 3)
                .delay(2,TimeUnit.SECONDS)
                .subscribe(new Observer < Integer > () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "=======================onSubscribe");
                    }
                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "=======================onNext " + integer);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "=======================onComplete");
                    }
                });

    }




}
