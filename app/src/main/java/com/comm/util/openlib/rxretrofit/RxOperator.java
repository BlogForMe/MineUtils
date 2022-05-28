package com.comm.util.openlib.rxretrofit;

import android.util.Log;
import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jon on 3/30/18.
 * https://blog.csdn.net/niubitianping/article/details/56288452
 */

public class RxOperator {

    public static String TAG = "rxOperator";

    public static void testOnErrorReturn() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int i = 0; i <= 3; i++) {
                    if (i == 2) {
                        e.onError(new Throwable("出现错误了"));
                    } else {
                        e.onNext(i + "");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "在onErrorReturn处理了: " + throwable);
                        //拦截到错误之后，返回一个结果发射，然后就正常结束了。
                        return "1";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "收到消息: " + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "结果错误: " + throwable);
                    }
                });
    }


    public static void testOnErrorResumeReturn() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int i = 0; i <= 3; i++) {
                    if (i == 2) {
                        //这里是Throwable
                        e.onError(new Throwable("出现错误了"));
                    } else {
                        e.onNext(i + "");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends String>>() {
                    @Override
                    public ObservableSource<? extends String> apply(@NonNull Throwable throwable) throws Exception {
                        //拦截到错误之后，重新定义了被观察者
                        return Observable.just("重新定义了被观察者");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "收到消息: " + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "结果错误: " + throwable);
                    }
                });
    }



    public static void testRetry(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for(int i = 0; i<= 3 ;i++){
                    if(i == 2){
                        e.onError(new Exception("出现错误了"));
                    }else{
                        e.onNext(i+"");
                    }
                    try{
                        Thread.sleep(1000);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.newThread())
                /*.retry(new Predicate<Throwable>() {
                    @Override
                    public boolean test(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "retry错误: "+throwable.toString());

                        //返回假就是不让重新发射数据了，调用观察者的onError就终止了。
                        //返回真就是让被观察者重新发射请求
                        return true;
                    }
                })*/
                /*.retry(new BiPredicate<Integer, Throwable>() {
                    @Override
                    public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "retry错误: "+integer+" "+throwable.toString());

                        //返回假就是不让重新发射数据了，调用观察者的onError就终止了。
                        //返回真就是让被观察者重新发射请求
                        return true;
                    }
                })*/
                .retry(3)    //最多让被观察者重新发射数据3次
//                .retry(3, new Predicate<Throwable>() {
//                    @Override
//                    public boolean test(@NonNull Throwable throwable) throws Exception {
//                        Log.e(TAG, "retry错误: "+throwable.toString());
//                        //最多让被观察者重新发射数据3次，但是这里返回值可以进行处理
//                        //返回假就是不让重新发射数据了，调用观察者的onError就终止了。
//                        //返回真就是让被观察者重新发射请求
//                        return true;
//                    }
//                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "收到消息: " + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "结果错误: " + throwable);
                    }
                });
    }


    public static void testRetryWhen() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int i = 0; i <= 3; i++) {
                    if (i == 2) {
                        e.onError(new Exception("出现错误了"));
                    } else {
                        e.onNext(i + "");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.newThread())
              /*  .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {

                        //这里可以发送新的被观察者 Observable
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {

                                //如果发射的onError就终止
                                return Observable.error(new Throwable("retryWhen终止啦"));
                            }
                        });

                    }
                })*/

                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "收到消息: " + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "结果错误: " + throwable);
                    }
                });

    }
}
