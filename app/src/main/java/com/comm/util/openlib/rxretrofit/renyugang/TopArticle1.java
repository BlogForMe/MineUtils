package com.comm.util.openlib.rxretrofit.renyugang;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * https://mp.weixin.qq.com/s/RkGHpVSpngfHDXo4Es-a9w
 */
public class TopArticle1 {
    public static String TAG = "TopArticle";
    static Integer i = 100;

    public static void demo1() {
        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "=========================currentThread name: " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });


        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "======================onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "======================onComplete");
            }
        };

        observable.subscribe(observer);
    }

    public static void create() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello Observer");
                e.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                Log.d("chan", "=============onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                Log.d("chan", "=============onComplete ");
            }
        };
        observable.subscribe(observer);
    }

    public static void just() {
        Observable.just(1, 2, 3)
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

    public static void fromArray() {
        Integer[] array = {1, 2, 3, 4};
        Observable.fromArray(array)
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

    public static void fromCallable() {
        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "================accept " + integer);
                    }
                });
    }

    public static void fromFuture() {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Log.d(TAG, "CallableDemo is Running");
                return "返回结果";
            }
        });
        Observable.fromFuture(futureTask)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        futureTask.run();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "================accept " + s);
                    }
                });
    }

    public static void fromIterable() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        Observable.fromIterable(list)
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

    public static void defer() {
        // i 要定义为成员变量
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });
        i = 200;
        Observer observer = new Observer<Integer>() {
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
        };
        observable.subscribe(observer);
        i = 300;
        observable.subscribe(observer);
    }


    public static void timer() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "===============onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public static void interval() {
        Observable.interval(4, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "==============onSubscribe ");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "==============onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public static void intervalRange() {
        Observable.intervalRange(2, 5, 2, 1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "==============onSubscribe ");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "==============onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public static void range() {
        Observable.range(2, 5)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "==============onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer aLong) {
                        Log.d(TAG, "==============onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public static void empty() {
//        Observable.empty()
//        Observable.never()
        Observable.error(new NullPointerException())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "==================onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, "==================onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "==================onError " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "==================onComplete");
                    }
                });
    }

    public static void map() {
//        Observable.just(1, 2, 3)
//                .map(new Function< Integer, String >() {
//                    @Override
//                    public String apply(Integer integer) throws Exception {
//                        return "I'm " + integer;
//                    }
//                })
//                .subscribe(new Observer < String > () {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.e(TAG, "===================onSubscribe");
//                    }
//                    @Override
//                    public void onNext(String s) {
//                        Log.e(TAG, "===================onNext " + s);
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//                    @Override
//                    public void onComplete() {
//                    }
//                });


        List<Person> personList = new LinkedList<>();
        Observable.fromIterable(personList)
                .map(new Function<Person, List<Plan>>() {
                    @Override
                    public List<Plan> apply(Person person) throws Exception {
                        return person.getPlanList();
                    }
                }).subscribe(new Observer<List<Plan>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Plan> plans) {
                for (Plan plan : plans) {
                    List<String> planActionList = plan.getActionList();
                    for (String action : planActionList) {
                        Log.d(TAG, "==================action " + action);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    public static void flatMap() {
        List<Person> personList = new LinkedList<>();
        Observable.fromIterable(personList)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) throws Exception {
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .flatMap(new Function<Plan, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Plan plan) throws Exception {
                        return Observable.fromIterable(plan.getActionList());
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void concatMap() {
        List<Person> personList = new LinkedList<>();
        Observable.fromIterable(personList)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) throws Exception {
                        if ("chan".equals(person.getName())) {
                            return Observable.fromIterable(person.getPlanList()).delay(10, TimeUnit.MILLISECONDS);
                        }
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .subscribe(new Observer<Plan>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Plan plan) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public  static  void  buffer(){
        Observable.just(1, 2, 3, 4, 5,6)
                .buffer(3, 2)
                .subscribe(new Observer < List < Integer >> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(List < Integer > integers) {
                        Log.d(TAG, "================缓冲区大小： " + integers.size());
                        for (Integer i: integers) {
                            Log.d(TAG, "================元素： " + i);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
