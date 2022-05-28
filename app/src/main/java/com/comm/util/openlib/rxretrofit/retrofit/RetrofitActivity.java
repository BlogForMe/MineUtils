package com.comm.util.openlib.rxretrofit.retrofit;

import java.io.IOException;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import timber.log.Timber;

public class RetrofitActivity extends AppCompatActivity {
    String TAG = "RetrofitActivity";
    StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        findViewById(R.id.bt_search).setOnClickListener(v -> {
//            okHttpRequest();
            retrofitRequest();
        });
    }

    private void retrofitRequest() {
        IApiStores iApiStores = RetrofitFactory.create(IApiStores.class);
        retrofit2.Call<List<SharedListBean>> sharedListCall = iApiStores.getSharedList(2, 1);
        sharedListCall.enqueue(new retrofit2.Callback<List<SharedListBean>>() {
            @Override
            public void onResponse(retrofit2.Call<List<SharedListBean>> call, retrofit2.Response<List<SharedListBean>> response) {
                List<SharedListBean> body = response.body();
                Timber.d(body.get(0).toString());
            }

            @Override
            public void onFailure(retrofit2.Call<List<SharedListBean>> call, Throwable t) {

            }
        });


    }

    public void okHttpRequest() {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("city", "长沙")
                .add("key", "13cb58f5884f9749287abbead9c658f2")
                .build();
        Request request = new Request.Builder()
                .url("http://restapi.amap.com/v3/weather/weatherInfo")
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Log.i(TAG, "onResponse: " + res);
            }
        });

    }


    private void sendRxJava(String txt) {
        ArrayMap<String, Object> arrayMap = new ArrayMap<>();
        arrayMap.put("city", "长沙");
        arrayMap.put("key", "13cb58f5884f9749287abbead9c658f2");
        arrayMap.put("content", txt);
        IApiStores iApiStores = RetrofitFactory.create(IApiStores.class);
        iApiStores.getTTs(arrayMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String rs = responseBody.string();
                        Timber.i("rs    " + rs);
                        if (!TextUtils.isEmpty(rs)) {
                            JSONObject jsonObject = new JSONObject(rs);
                            String wavfile = jsonObject.getString("wavfile");
                            String result = jsonObject.getString("result");
                            Timber.i("result    " + result);
                            stringBuilder.append(result)
                                    .append(",");
                        }
                    }
                });
    }


}
