package com.comm.util.openlib.rxretrofit.xiangx;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.openlib.rxretrofit.xiangx.core.Emitter;
import com.comm.util.openlib.rxretrofit.xiangx.core.Observable;
import com.comm.util.openlib.rxretrofit.xiangx.core.ObservableOnSubscribe;
import com.comm.util.openlib.rxretrofit.xiangx.core.Observer;
import com.comm.util.openlib.rxretrofit.xiangx.core.mapop.Function;


/**
 * 手写 RxJava
 */

public class RxImlActivity extends AppCompatActivity {
    public static final String TAG = "RxImlActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_iml);
    }


    public void bt_rx_send(View view) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("大家好");
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                s += "1111";
                return s;
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply( String s) {
                s += "222";
                return s;
            }
        }).subscribeObserver(new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "reciceve " + s);
            }

            @Override
            public void onSubscribe() {

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