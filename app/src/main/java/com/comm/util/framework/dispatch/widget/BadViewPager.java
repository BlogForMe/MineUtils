package com.comm.util.framework.dispatch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class BadViewPager extends ViewPager {

    private static final String TAG = "BadViewPager";
    private int mLastXIntercep, mLastYIntercep;

    public BadViewPager(@NonNull Context context) {
        super(context);
    }

    public BadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 外部拦截法
     *
     * @param ev
     * @return
     */
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        boolean intercepted = false;
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//
//        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                intercepted = false;
//                //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId
//                super.onInterceptTouchEvent(ev);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //横坐标位移增量
//                int deltaX = x - mLastXIntercep;
//                int deltaY = y - mLastYIntercep;
//                //纵坐标位移增量
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    intercepted = true;
//                } else {
//                    intercepted = false;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                intercepted = false;
//                break;
//            default:
//                break;
//        }
//        mLastXIntercep = x;
//        mLastYIntercep = y;
//
//        Log.i(TAG, "intercepted = " + intercepted);
//        return intercepted;
//    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_DOWN) {
            super.onInterceptTouchEvent(ev);
            return false;
        }
        return true;
    }
}
