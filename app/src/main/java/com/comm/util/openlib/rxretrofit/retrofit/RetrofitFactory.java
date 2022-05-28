package com.comm.util.openlib.rxretrofit.retrofit;

import java.util.concurrent.TimeUnit;

import com.comm.util.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jon on 3/25/18.
 */

public class RetrofitFactory {
    //查询网络的Cache-Control设置
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    public static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=10";
    private static final long CONNECT_TIMEOUT = 60L;

//    public final static String trueUrl = "http://120.77.48.22:8080";
//    public final static String trueUrl = "http://125.77.202.194:3340/";
    private static final long READ_TIMEOUT = 60L;
    private static final long WRITE_TIMEOUT = 60L;
    //设缓存有效期为1天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    private static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";
    public static String CONSTANT_URL = "/csn_hospital_APIServer/RestService/Call/";
//    public static String trueUrl = "https://" + "dev" + ".casanubeserver.com"+CONSTANT_URL;
    public static String trueUrl = "https://www.fastmock.site/mock/a883d7ed16db1dd2756088ab99fbbae5/emailapp/";
    public static String CONFIG_URL = "https://registry.casanubeserver.com/";
    private static volatile OkHttpClient mOkHttpClient;


    /**
     * 获取OkHttpClient实例
     *
     * @return
     */
    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitFactory.class) {
//                Cache cache = new Cache(new File(App.getAppContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //设置Debug Log模式
                    builder.addInterceptor(loggingInterceptor);
                }
                mOkHttpClient = builder
                        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                        .build();
            }
        }
        return mOkHttpClient;
    }

    public static <T> T configRetrofitService(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONFIG_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(clazz);
    }


    /**
     * 获取Service
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(trueUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(clazz);
    }



}
