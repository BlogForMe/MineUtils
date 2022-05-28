//package com.comm.util.openlib.rxretrofit;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//
//import com.comm.util.R;
//import com.comm.util.RetryWithDelay;
//import com.comm.util.ndk.JYSoActivity;
//
//import java.io.IOException;
//
//import io.reactivex.Observable;
//import io.reactivex.ObservableEmitter;
//import io.reactivex.ObservableOnSubscribe;
//import io.reactivex.ObservableSource;
//import io.reactivex.Observer;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.functions.Function;
//
//public class RxJava2Activity extends AppCompatActivity {
//    private static String TAG = "RxJava2Activity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rx_java2ctivity);
//        initVIew();
//
//        findViewById(R.id.bt_retrywhen).setOnClickListener(v -> {
//            retryWhen();
//        });
//
//        Intent intent=new Intent(this, JYSoActivity.class);
//        startActivity(intent);
//        startActivityForResult(intent,1000);
//    }
//
//    private void initVIew() {
////        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, new RxJava2Fragment()).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, new TestFragment()).commit();
//    }
//
//    public void testRxjava(View view) {
//        RxJavaBasic.just();
//    }
//
//
//    private void retryWhen() {
//        {
//            Observable.create(new ObservableOnSubscribe<String>() {
//
//                @Override
//                public void subscribe(ObservableEmitter<String> e) throws Exception {
//                    e.onNext("chan");
//                    e.onNext("ze");
//                    e.onNext("de");
////                    e.onError(new Exception("404"));
//                    e.onNext("haha");
//                }
//            })
//                    .retryWhen(new RetryWithDelay(3, 3000))
//
//                    .subscribe(new Observer<String>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                            Log.d(TAG, "==================onSubscribe ");
//                        }
//
//                        @Override
//                        public void onNext(String s) {
//                            Log.d(TAG, "==================onNext " + s);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.d(TAG, "==================onError " + e.getMessage());
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            Log.d(TAG, "==================onComplete ");
//                        }
//                    });
//        }
//    }
//
//}
//
