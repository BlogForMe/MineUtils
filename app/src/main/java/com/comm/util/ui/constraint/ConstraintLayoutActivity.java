package com.comm.util.ui.constraint;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

public class ConstraintLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
//        setContentView(R.layout.activity_constraint_new);

//        TimeChronometerUtil timeCountUtil = new TimeChronometerUtil();
//        Chronometer chronometer = findViewById(R.id.chronometer_txt);
//        findViewById(R.id.tv_room_address).setOnClickListener(v->{
//            timeCountUtil.start();
//        });
//
//        findViewById(R.id.iv_img01).setOnClickListener(v->{
//            chronometer.stop();
//        });

       findViewById(R.id.bt_b1).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               findViewById(R.id.gp_1).setVisibility(View.GONE);
           }
       });
           findViewById(R.id.bt_b2).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               findViewById(R.id.gp_2).setVisibility(View.INVISIBLE);
           }
       });


    }
}
