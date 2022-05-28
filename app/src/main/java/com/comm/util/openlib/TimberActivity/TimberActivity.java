package com.comm.util.openlib.TimberActivity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import timber.log.Timber;

public class TimberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timber);
    }


    /**
     * 获取maci
     * @param view
     */
    public  void  btGetMac(View view){
//        String macAddresss = DeviceUtil.getAdresseMAC(this);
//        Timber.d("macAddresss  " +macAddresss);

    }


    public void btLOG(View v) {
/*
    // LogNotTimber
    Log.d("TAG", "msg");
    Log.d("TAG", "msg", new Exception());
    android.util.Log.d("TAG", "msg");
    android.util.Log.d("TAG", "msg", new Exception());


    // StringFormatInTimber
    Timber.w(format("%s", getString()));
    Timber.w(format("%s", getString()));

    // ThrowableNotAtBeginning
    Timber.d("%s", new Exception());


    // BinaryOperationInTimber
    String foo = "foo";
    String bar = "bar";
    Timber.d("foo" + "bar");
    Timber.d("foo" + bar);
    Timber.d(foo + "bar");
    Timber.d(foo + bar);


    // TimberArgCount
    Timber.d("%s %s", "arg0");
    Timber.d("%s", "arg0", "arg1");
    Timber.tag("tag").d("%s %s", "arg0");
    Timber.tag("tag").d("%s", "arg0", "arg1");


    // TimberArgTypes
    Timber.d("%d", "arg0");
    Timber.tag("tag").d("%d", "arg0");
     */

    // TimberTagLength
    Timber.tag("abcdefghijklmnopqrstuvwx");
    Timber.tag("abcdefghijklmnopqrstuvw" + "x");

    // TimberExceptionLogging
    Timber.d(new Exception(), new Exception().getMessage());
    Timber.d(new Exception(), "");
    Timber.d(new Exception(), null);
     /*
    */
    }
    private String getString() {
        return "foo";
    }

}
