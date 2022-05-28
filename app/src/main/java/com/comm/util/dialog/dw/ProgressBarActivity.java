package com.comm.util.dialog.dw;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.dialog.dw.progress.ColorArcProgressBar;

public class ProgressBarActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_progress_bar);
//        ProgressBar pbBar = findViewById(R.id.pb_bar);
//
//        findViewById(R.id.bt_process).setOnClickListener(v -> {
//            pbBar.setProgress(50);
//        });
//    }

    int i=0;
    Handler hdlr =new Handler();
    private ProgressBar pbPicUp;
    private Button button1;
    private ColorArcProgressBar bar1;
    private Button button2;
    private ColorArcProgressBar bar2;
    private Button button3;
    private ColorArcProgressBar bar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        pbPicUp = findViewById(R.id.pb_pic_up);



//        bar1 = (ColorArcProgressBar) findViewById(R.id.bar1);
//        button1 = (Button) findViewById(R.id.button1);
//        bar2 = (ColorArcProgressBar) findViewById(R.id.bar2);
//        button2 = (Button) findViewById(R.id.button2);
//        bar3 = (ColorArcProgressBar) findViewById(R.id.bar3);
//        button3 = (Button) findViewById(R.id.button3);

//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bar1.setCurrentValues(100);
//            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bar2.setCurrentValues(100);
//            }
//        });
//
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bar3.setCurrentValues(77);
//            }
//        });

        GradientDrawable pb = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{Color.RED, Color.MAGENTA, Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW, Color.RED});



        findViewById(R.id.bt_progress).setOnClickListener(view->{
            setPrograssBar();
        });
    }

    private void setPrograssBar() {
//        for (int i = 0; i <= 100; i++) {
////            try {
////                Thread.sleep(500);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            pbPicUp.setProgress(i);
//            Timber.i("i " + i);
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (i < 100) {
                    i += 1;
                    // Update the progress bar and display the current value in text view
                    hdlr.post(new Runnable() {
                        public void run() {
                            pbPicUp.setProgress(i);
                        }
                    });
                    try {
                        // Sleep for 100 milliseconds to show the progress slowly.
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
