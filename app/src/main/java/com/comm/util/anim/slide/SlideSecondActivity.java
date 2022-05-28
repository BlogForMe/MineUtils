package com.comm.util.anim.slide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import timber.log.Timber;

public class SlideSecondActivity extends AppCompatActivity {

    private int screenWith;
    private int screenHeight;
    private float downX;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_second);
        Timber.i("screenWith    " + screenWith + "screenHeight " + screenHeight);
        decorView = getWindow().getDecorView();
//        screenWith = DisplayUtils.getScreenW(this);
//        screenHeight = DisplayUtils.getScreenH(this);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveDistanceX = event.getX() - downX;
                if (moveDistanceX > 0) {
                    decorView.setX(moveDistanceX);
                }
                break;
            case MotionEvent.ACTION_UP:
                moveDistanceX = event.getX() - downX;
                if (moveDistanceX > screenWith / 2) {
//                    finish();
                    continudeMove(moveDistanceX);
                } else {
                    rebackToLeft(moveDistanceX);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void continudeMove(float moveDistanceX) {
        ValueAnimator anim = ValueAnimator.ofFloat(moveDistanceX, screenWith);
        anim.setDuration(1000);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) anim.getAnimatedValue();
                decorView.setX(x);
            }
        });

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
            }
        });
    }

    private void rebackToLeft(float moveDistanceX) {
        ObjectAnimator.ofFloat(decorView, "X", moveDistanceX, 0).setDuration(300).start();
    }
}
