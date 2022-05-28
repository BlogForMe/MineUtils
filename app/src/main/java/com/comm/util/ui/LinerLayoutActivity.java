package com.comm.util.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

public class LinerLayoutActivity extends AppCompatActivity {
    public static int num = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liner_layout);
//        setContentView(R.layout.activity_health_plan);

//        LinearLayout llVertical = findViewById(R.id.ll_plan_one);
////        LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//
//        LayoutInflater inflate = LayoutInflater.from(this);
//        View view1 = inflate.inflate(R.layout.layout_animation, null);
//        View view2 = inflate.inflate(R.layout.ic_hpan_suger_press_y, null);
//        View view3 = inflate.inflate(R.layout.ic_hpan_suger_press_y, null);
////        View view3 = inflate.inflate(R.layout.head_view, null);
//
//        llVertical.addView(view1);
//        llVertical.addView(view2);
//        llVertical.addView(view3);
//        while (num > 0) {
//            num--;
//            TextView textView = new TextView(this);
//            textView.setLayoutParams(tvLp);
//            textView.setText("你好");
//            llVertical.addView(textView);
//        }
    }

}
