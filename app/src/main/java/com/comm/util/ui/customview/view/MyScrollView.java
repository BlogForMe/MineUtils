package com.comm.util.ui.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;
import timber.log.Timber;

/*********************************************
 * author: Blankj on 2016/7/20 13:03
 * blog:   http://blankj.com
 * e-mail: blankj@qq.com
 *
 * 这种方式没有计算 child的位置，不好控制第一个和最后一个的滑动
 * 还是用android开发艺术探索那个好一点
 *********************************************/
public class MyScrollView extends ViewGroup {

    private int mScreenHeight;
    private Scroller mScroller;
    private int mLastY;
    private int mStart;
    private int mEnd;


    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScroller = new Scroller(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        // 设置ViewGroup的高度
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight * childCount;
        setLayoutParams(mlp);
        for (int i = 0; i < childCount; ++i) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 滑动时候Y 触摸起点
                mStart = getScrollY();
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;

                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                // 滑动时候Y 触摸终点
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;

                Timber.d("dScrollY " +dScrollY);
                if (dScrollY > 0) {
                    if (dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, mScreenHeight - dScrollY);
                    }
                } else {
                    if (-dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -mScreenHeight - dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }
}