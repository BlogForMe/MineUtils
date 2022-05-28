package com.comm.util.anim.textview;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/**
 * @author : John
 * @date : 2018/8/14
 */
class FlashHelper {
    private static final FlashHelper ourInstance = new FlashHelper();

    private FlashHelper() {
    }

    static FlashHelper getInstance() {
        return ourInstance;
    }

    public void startFlick(View view) {
        if (view == null) {
            return;
        }
        Animation alphaAnimation = new AlphaAnimation(1, 0.5f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(alphaAnimation);
    }

    public void stopFlick(View view) {
        if (view == null) {
            return;
        }
        view.clearAnimation();
    }


}
