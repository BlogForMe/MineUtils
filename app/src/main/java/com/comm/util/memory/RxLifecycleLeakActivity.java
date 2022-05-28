package com.comm.util.memory;

import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * https://www.jianshu.com/p/bdfd2a6b2681
 * 内存泄漏使用方法
 */

public class RxLifecycleLeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_lifecycle_leak);
        initData();
    }

    private void initData() {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxLifecycle.<Long>bindUntilEvent(ActivityEvent.DESTROY))  //这里有问题
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Timber.i("接收数据 " + aLong);
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

