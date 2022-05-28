package com.comm.util.ui.tool;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import timber.log.Timber;

public class BlinkActivity extends AppCompatActivity {

    private View view;
    private long i;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {

            } else if (msg.what == 1) {
                i++;
                Timber.i("handler " + i);
                if (i % 2 == 0) {
                    view.setVisibility(View.INVISIBLE);
                } else {
                    view.setVisibility(View.VISIBLE);
                }
                handler.sendEmptyMessageDelayed(1, 500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blink);

        view = findViewById(R.id.view);
        findViewById(R.id.bt_blink).setOnClickListener(v -> {
            handler.sendEmptyMessageDelayed(1, 500);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(null);
    }
}
