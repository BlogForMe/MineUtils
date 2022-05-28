package com.comm.util.ui.customview;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.ui.customview.view.DrawThreadCradio;
import com.comm.util.ui.customview.view.StaticReceive;
import com.comm.util.utils.RawAssertUtil;
import timber.log.Timber;

public class ElectLkActivity extends AppCompatActivity {

    private DrawThreadCradio drawRunable;
    private Thread drawThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elect_lk);
        drawRunable = findViewById(R.id.main_pc80B_view_draw);

        StaticReceive.mECGReplayBuffer = RawAssertUtil.loadDatas(this, R.raw.ecgdata);
        Timber.i("输出");


    }

    public void showDraw(View view) {
        if (drawThread == null) {
            drawThread = new Thread(drawRunable, "DrawPC80BThread");
            drawThread.start();
        }
        if (drawRunable.isPause()){
            drawRunable.Continue();
        }
    }

    public void stopDraw(View view){
       if (drawRunable.isStop()){
           drawRunable.Stop();
       }
    }
}
