package com.comm.util.openlib.rxretrofit;

import android.view.View;
import butterknife.OnClick;
import com.comm.util.R;
import com.comm.util.base.BaseFragment;
import com.comm.util.openlib.rxretrofit.Season_zlc.rxdemo07;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by jon on 12/9/17.
 * http://www.10tiao.com/html/169/201709/2650823932/1.html
 * 给初学者的RxJava2.0教程(一)
 */

public class RxJava2Fragment extends BaseFragment {


    @Override
    protected void initView(View rootView) {
//        Timber.d(Thread.currentThread().getName());
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_rxjava2;
    }

    /**
     * 给初学者的RxJava2.0教程(一)
     */

    @OnClick({R.id.bt_00f, R.id.bt_01f})
    public void button(View v) {
        switch (v.getId()) {
            case R.id.bt_00f:
                bt00();
                break;
            case R.id.bt_01f:
                bt01();
                break;
        }
    }

    /**
     * 给初学者的RxJava2.0教程(二)
     */
    @OnClick({R.id.bt_10f})
    public void button10(View v) {
        switch (v.getId()) {
            case R.id.bt_10f:
                bt10();
                break;
        }
    }


    /**
     * 给初学者的RxJava2.0教程(五)
     */
    @OnClick({R.id.bt_40f})
    public void button40(View v) {
        switch (v.getId()) {
            case R.id.bt_40f:
                bt40();
                break;
        }
    }

    /**
     * 给初学者的RxJava2.0教程(六)
     */
    @OnClick({R.id.bt_50f})
    public void button50(View v) {
        switch (v.getId()) {
            case R.id.bt_50f:
                bt50();
                break;
        }
    }


    /**
     * 给初学者的RxJava2.0教程(七)
     *
     * @param v
     */
    @OnClick({R.id.bt_60f})
    public void button60(View v) {
        switch (v.getId()) {
            case R.id.bt_60f:
                rxdemo07.bt60();
                break;
            case R.id.bt_61f:
                rxdemo07.request(1);
                break;
        }
    }




    private void bt50() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Timber.d(" " + integer);
                    }
                });
    }


    private void bt40() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) { //无限循环发事件
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io());
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {


            @Override
            public String apply(Integer integer, String s) throws Exception {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Timber.d(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.d(throwable);
                    }
                });


    }


    /**
     *
     */

    private void bt10() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Timber.d("Observable thread is : " + Thread.currentThread().getName());
                Timber.d("emit 1");
                e.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Timber.d("Observable thread is : " + Thread.currentThread().getName());
                Timber.d("onNext : " + integer);
            }
        };
        observable
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(consumer);
    }


    private void bt01() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Timber.d("emit 1");
                emitter.onNext(1);
                Timber.d("emit 2");
                emitter.onNext(2);
                Timber.d("emit 3");
                emitter.onNext(3);
                Timber.d("emit complete");
                emitter.onComplete();
                Timber.d("emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                Timber.d("onSubscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Timber.d("onNext :" + integer);
                i++;
                if (i == 2) {
                    Timber.d("dispose");
                    mDisposable.dispose();
                    Timber.d("isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("error");
            }

            @Override
            public void onComplete() {
                Timber.d("onComplete");
            }
        });
    }

    private void bt00() {
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Timber.d("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Timber.d(" " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Timber.d(e);
            }

            @Override
            public void onComplete() {
                Timber.d("complete");
            }
        };
        //建立连接
        observable.subscribe(observer);
    }


}
