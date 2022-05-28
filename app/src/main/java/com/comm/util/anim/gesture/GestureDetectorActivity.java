package com.comm.util.anim.gesture;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.anim.slide.SlideFirstActivity;
import com.comm.util.anim.slide.SlideSecondActivity;
import timber.log.Timber;

/**
 */
public class GestureDetectorActivity extends AppCompatActivity {

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);


        mGestureDetector = new GestureDetector(this, new MyGesture());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    class MyGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > 50) {
                Timber.i("左滑功能");
                Intent intent = new Intent(GestureDetectorActivity.this, SlideFirstActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_from_left);
                return true;
            } else if (e2.getX() - e1.getX() > 50) {
                Timber.i("右滑功能");
                Intent intent = new Intent(GestureDetectorActivity.this, SlideSecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_from_right);
                return true;
            }
            return false;
        }
    }

}
