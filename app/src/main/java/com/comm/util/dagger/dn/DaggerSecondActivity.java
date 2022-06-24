package com.comm.util.dagger.dn;

import javax.inject.Inject;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.MyApplication;
import com.comm.util.R;
import com.comm.util.dagger.dn.di.ApiService;

public class DaggerSecondActivity extends AppCompatActivity {
    String TAG = "SecondActivity";

    @Inject
    ApiService apiService3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        MyApplication.getApplicationComponent1().inject(this);
        //DaggerApplicationComponent相当于DaggerApplication的实现类
        //DaggerApplicationComponent1.create().inject(this);//DaggerApplicationComponent相当于DaggerApplication的实现类
        Log.i(TAG, "apiService3: " + apiService3);

    }
}