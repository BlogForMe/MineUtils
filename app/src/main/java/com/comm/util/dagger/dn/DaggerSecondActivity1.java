package com.comm.util.dagger.dn;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

public class DaggerSecondActivity1 extends AppCompatActivity {
    String TAG = "DaggerSecondActivity";

    //@Inject
    //ApiService apiService3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        //MyApplication.getApplicationComponent1().inject(this);
        //DaggerApplicationComponent相当于DaggerApplication的实现类
        //DaggerApplicationComponent1.create().inject(this);//DaggerApplicationComponent相当于DaggerApplication的实现类
        //Log.i(TAG, "apiService3: " + apiService3);

    }
}