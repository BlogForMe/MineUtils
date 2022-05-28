package com.comm.util.openlib.rxretrofit.Season_zlc;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import timber.log.Timber;

/**
 * Created by jon on 12/10/17.
 */

public class rxdemo07 {

    static Subscription mSubscription;

    public static void request(long n) {
        mSubscription.request(n);
    }

    public static void bt60() {
        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
//                Timber.d("emit 1");
//                emitter.onNext(1);
//                Timber.d("emit 2");
//                emitter.onNext(2);
//                Timber.d("emit 3");
//                emitter.onNext(3);
//                Timber.d("emit complete");
//                emitter.onComplete();
                for (int i=0;;i++){
                    Timber.d("emitter " +i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.DROP);//增加了一个参数

        Subscriber<Integer> downstream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Timber.d("onSubscribe");
//                s.request(2);
                mSubscription = s;
            }

            @Override
            public void onNext(Integer integer) {
                Timber.d("onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Timber.d("onError: " + t);
            }

            @Override
            public void onComplete() {
                Timber.d("onComplete");
            }
        };
        upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(downstream);
    }
}
