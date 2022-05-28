package com.comm.util;

import com.alibaba.android.arouter.facade.annotation.Route;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

@Route(path = "/app/BeaconActivity")
public class BeaconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);
    }
}
