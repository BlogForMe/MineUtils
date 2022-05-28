package com.comm.util.anim.slide;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

public class SlideFirstActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_first);
        findViewById(R.id.bt_first).setOnClickListener(v -> {

            startActivity(new Intent(this, SlideSecondActivity.class));
        });
    }
}
