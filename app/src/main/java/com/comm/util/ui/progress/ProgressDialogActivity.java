package com.comm.util.ui.progress;

import java.util.concurrent.Executors;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

/**
 * 进度条
 */
public class ProgressDialogActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            mProgressDialog.setProgress(msg.what);
            Log.i("ProgressDialogActivity ", "计数  " + msg.what);
            mProgressDialog.setProgress(msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        initProgress();
    }

    private void initProgress() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setTitle("下载中");
        mProgressDialog.setMax(100);
    }

    public void btProgress(View v) {
        mProgressDialog.show();
//
        Executors.newCachedThreadPool().submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100;i++) {
                    try {
                        Thread.sleep(200);
                        handler.sendEmptyMessage(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

}