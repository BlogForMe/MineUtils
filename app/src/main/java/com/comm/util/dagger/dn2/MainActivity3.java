package com.comm.util.dagger.dn2;

import javax.inject.Inject;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

public class MainActivity3 extends AppCompatActivity {
    String TAG = "MainActivity3";
    @Inject
    User3 user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //DaggerApp
        DaggerApplicationComponent3.create().inject(this);
        Log.i(TAG, "onCreate: user= " + user);
    }

}