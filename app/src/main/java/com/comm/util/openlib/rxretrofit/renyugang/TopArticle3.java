package com.comm.util.openlib.rxretrofit.renyugang;

import java.io.IOException;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Function;

public class TopArticle3 {

    private static final String TAG = "TopArticle3";
    //这个重新发送不太理解?
    private static int i = 0;

    public static void retry() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Exception("404"));
            }
        }).retry(1)
                .subscribe(new Observer<Integer>() {
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
                        Log.d(TAG, "==================onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "==================onComplete ");
                    }
                });

    }

    public static void retryUntil() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Exception("404"));
            }
        })
                .retryUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        Log.d(TAG, "==================getAsBoolean " + i);

                        return i == 6;
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                i += integer;
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }


    public static void retryWhen() {
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("chan");
                e.onNext("ze");
                e.onNext("de");
//                e.onError(new Exception("404"));
                e.onNext("haha");
            }
        })
//                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Observable<Throwable> throwable) throws Exception {
//                        String thxStr = throwable.toString();
//                        if (!thxStr.equals("404")) {
//                            return Observable.just("可以忽略的异常");
//                        } else {
//                            return Observable.error(new Throwable("终止啦"));
//                        }
//                    }
//                })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> errors) throws Exception {
                        errors.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable error) throws Exception {
                                if (error instanceof  IOException){
                                    return  Observable.just(null);
                                }
                              return   Observable.error(error);
                            }
                        });
                        return  null;
                    }
                })
//                .retryWhen(errors -> errors.flatMap(error -> {
//                    if (error instanceof IOException) {
//                        return Observable.just(null);
//                    }
//
//                    return Observable.error(error);
//                }))

                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "==================onSubscribe ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "==================onNext " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "==================onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "==================onComplete ");
                    }
                });
    }

    public static void repeat() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
                .repeat(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "===================onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "===================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "===================onComplete ");
                    }
                });
    }


}

