package com.comm.util.dagger.dn;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.dagger.dn.di.UserComponent;

public class DaggerUserActivity extends AppCompatActivity {
    String  TAG = "DaggerUserActivity";
    //@Inject
    //User1 user;
    //
    //@Inject
    //User1 user2;

    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_user);

        //userComponent= DaggerUserComponent.builder().applicationComponent2(MyApplication.getApplicationComponent2()).build();
        //userComponent.inject(this);
        //Log.i(TAG, "user: " + user);
        //Log.i(TAG, "user2: " + user2);
    }
}