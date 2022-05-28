package com.comm.util.memory;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;


public class MemoryMonitorActivity extends AppCompatActivity {

    private TextView tvInit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_monitor);

        UserManger userManger = UserManger.getInstance(this);

         tvInit = findViewById(R.id.tv_int);


    }


    /**
     * 内存泄漏
     *
     * @param view
     */
    public void btLeak(View view) {
//        Intent intent = new Intent(this, RxLifecycleLeakActivity.class);
////        startActivity(intent);
        int i=7;
        tvInit.setText(i);

    }
}
