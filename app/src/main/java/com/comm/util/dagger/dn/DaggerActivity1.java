package com.comm.util.dagger.dn;

import javax.inject.Inject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.MyApplication;
import com.comm.util.R;
import com.comm.util.dagger.dn.di.ApiService;
import com.comm.util.dagger.dn.di.User;
import retrofit2.Retrofit;

public class DaggerActivity1 extends AppCompatActivity {
    String TAG = "DaggerActivity1";

    //3.设置Inject注解
    @Inject
    User user;

    @Inject
    User user2;

    @Inject
    Retrofit retrofit;

    @Inject
    ApiService apiService;

    @Inject
    ApiService apiService2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        //4.执行注入动作
        //DaggerApplicationComponent1.create().inject(this);//DaggerApplicationComponent相当于DaggerApplication的实现类
        MyApplication.getApplicationComponent1().inject(this);

        Log.i(TAG, "user: " + user);
        Log.i(TAG, "user2: " + user2);

        Log.i(TAG, "retrofit: " + retrofit);

        Log.i(TAG, "apiService: " +apiService);
        Log.i(TAG, "apiService2: " +apiService2);

        startActivity(new Intent(this, DaggerSecondActivity.class));
    }
}