package com.comm.util.openlib.rxretrofit.renyugang;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.bean.BaseCount;
import com.comm.util.bean.ConfigEnv;
import com.comm.util.bean.SigleField;
import com.comm.util.openlib.rxretrofit.retrofit.IApiStores;
import com.comm.util.openlib.rxretrofit.retrofit.RetrofitFactory;
import com.comm.util.utils.GsonUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Response;
import timber.log.Timber;

import static com.comm.util.openlib.rxretrofit.renyugang.TopArticle1.interval;

/**
 *
 */
public class RxJavaRenActivity extends AppCompatActivity {

    private ArrayMap<String, Object> configMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_ren);
        initData();

        findViewById(R.id.bt_click).setOnClickListener(v -> {
//           demo1();
            /**
             *  1 . 创建操作符
             */
//            create();
//  创建一个被观察者，并发送事件，发送的事件不可以超过10个以上。
//            just();
// From 操作符,这个方法和 just() 类似，只不过 fromArray 可以传入多于10个的变量，并且可以传入一个数组。
//            fromArray();
//            fromCallable();
//            fromFuture();
//            fromIterable();
//            defer();
//            timer();
            interval();
//            intervalRange();
//              range();
//            empty();
/**
 * 2. 转换操作符
 */
//            map();
//            flatMap();
//            concatMap();
//        buffer();


//           concat();
//            concatArray();`

//            merge();

//            TopArticle2.doOnNext();
        });

        findViewById(R.id.bt_rx_compete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                retryWhen();
//                cacheUrl();
                removeDoOnNext();
            }
        });
    }

    private void initData() {
        configMap = new ArrayMap<>();
        configMap.put("env", "ENV_CS");
    }


    public void doOnNext() {
        ArrayMap<String, Object> configMap = new ArrayMap<>();
        configMap.put("env", "ENV_CS");
        Observable<BaseCount<List<ConfigEnv>>> envApi = RetrofitFactory.configRetrofitService(IApiStores.class).getUnitList();
        RetrofitFactory.configRetrofitService(IApiStores.class).getAppConfig(GsonUtil.loginnoDoctorIdBody(configMap))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求注册结果
                .doOnNext(new Consumer<BaseCount<SigleField>>() {
                    @Override
                    public void accept(BaseCount<SigleField> sigleFieldBaseCount) throws Exception {

                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<BaseCount<SigleField>, ObservableSource<BaseCount<List<ConfigEnv>>>>() {
                    @Override
                    public ObservableSource<BaseCount<List<ConfigEnv>>> apply(BaseCount<SigleField> sigleFieldBaseCount) throws Exception {
                        if (sigleFieldBaseCount.getMeta().getStatusCode()==0){
//                            return Observable.error(new Exception());
                        }
                        return envApi;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseCount<List<ConfigEnv>>>() {
                    @Override
                    public void accept(BaseCount<List<ConfigEnv>> listBaseCount) throws Exception {
                        Timber.i(" " + listBaseCount.getCount());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.i(throwable.getMessage());
                    }
                });

    }

    public void cacheUrl(){
        RetrofitFactory.configRetrofitService(IApiStores.class).getAppConfigSJ(GsonUtil.loginnoDoctorIdBody(configMap))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<BaseCount<SigleField>>>() {
                    @Override
                    public void accept(Response<BaseCount<SigleField>> baseCountResponse) throws Exception {
                        Request request = baseCountResponse.raw().request();
                        RequestBody requestBody = request.body();
                        String method = request.method();
                      String  cacheUrl = baseCountResponse.raw().request().url().toString();
//                      Timber.i(cacheUrl);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.i(throwable);
                    }
                });

    }

    public void removeDoOnNext() {

        Observable<BaseCount<List<ConfigEnv>>> envApi = RetrofitFactory.configRetrofitService(IApiStores.class).getUnitList();
        RetrofitFactory.configRetrofitService(IApiStores.class).getAppConfig(GsonUtil.loginnoDoctorIdBody(configMap))
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<BaseCount<SigleField>, ObservableSource<BaseCount<List<ConfigEnv>>>>() {
                    @Override
                    public ObservableSource<BaseCount<List<ConfigEnv>>> apply(BaseCount<SigleField> sigleFieldBaseCount) throws Exception {
                        if (sigleFieldBaseCount.getMeta().getStatusCode()==0){
//                            Timber.i(sigleFieldBaseCount.getData().getConfig());
                            return Observable.error(new Exception());
                        }
                        return envApi;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseCount<List<ConfigEnv>>>() {
                    @Override
                    public void accept(BaseCount<List<ConfigEnv>> listBaseCount) throws Exception {
                        Timber.i(" " + listBaseCount.getCount());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.i(throwable.getMessage());

                    }
                });

    }

    public void setCreate(){
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        });
    }

  private void retryWhen(){
       RetrofitFactory.configRetrofitService(IApiStores.class).getAppConfig(GsonUtil.loginnoDoctorIdBody(configMap))
               .subscribeOn(Schedulers.io())
               .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                  private int mRetryCount;
                   @Override
                   public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {

                       return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {

                           @Override
                           public ObservableSource<?> apply(Throwable throwable) throws Exception {
                               String errorMsg = throwable.getMessage();
                               long waitTime = 0;
                               waitTime = 2000;
                               Timber.e("发生错误，尝试等待时间=" + waitTime + ",当前重试次数=" + mRetryCount);
                               mRetryCount++;
                               return waitTime > 0 && mRetryCount <= 4 ? Observable.timer(waitTime, TimeUnit.MILLISECONDS) : Observable.error(throwable);
                           }
                       });
                   }
               })
               .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求注册结果
               .subscribe(new Consumer<BaseCount<SigleField>>() {
                   @Override
                   public void accept(BaseCount<SigleField> sigleFieldBaseCount) throws Exception {
                       Timber.i(sigleFieldBaseCount.toString());
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {

                   }
               });
   }


}
