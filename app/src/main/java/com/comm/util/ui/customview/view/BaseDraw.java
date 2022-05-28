package com.comm.util.ui.customview.view;

import java.math.BigDecimal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import timber.log.Timber;

public class BaseDraw extends View implements Runnable {

    protected boolean stop = false;
    protected boolean pause = false;
    protected Handler mHandler;
    /**
     * 保存一整屏数据的数组
     */
    protected Float[] data2draw;
    /**
     * X轴上两点的间隔
     */
    protected float stepx = 2;
    protected DisplayMetrics dm;
    /**
     * 当前数组插入点
     */
    protected int arraycnt = 0;
    /**
     * 当前view的高度 (px)
     */
    protected float height = 0;


    protected Paint paint;

    protected CornerPathEffect cornerPathEffect = new CornerPathEffect(20);
    protected float cut;

    public BaseDraw(Context context) {
        super(context);
        init(context);
    }

    public BaseDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BaseDraw(Context context, int w, int h) {
        super(context);
    }

    public static float decimalTwoPlace(float value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        float f2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return f2;
    }

    /**
     * 设置前景色
     */
    public void setcForecolor(int cForecolor) {
    }

    private void init(Context context) {
        WindowManager wmManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        dm = new DisplayMetrics();
        wmManager.getDefaultDisplay().getMetrics(dm);
        cut = dm.widthPixels - 15 * 2 * dm.density;

        Log.d("init ", "init   " + cut);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void Stop() {
        this.stop = true;
    }

    public void Pause() {
        this.pause = true;
    }

    public boolean isPause() {
        return this.pause;
    }

    public boolean isStop() {
        return this.stop;
    }

    public synchronized void Continue() {
        this.pause = false;
        this.notify();
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void run() {

    }

    /**
     * 将需要绘制的数据添加到数组中
     *
     * @param data
     */
    public void addData(float data) {
        if (data2draw != null) {
            data2draw[arraycnt] = data;
            arraycnt = (arraycnt + 1) % data2draw.length;
            Timber.i("addData   arraycnt    " + arraycnt);
            postInvalidate();
        }
    }

    /**
     * 清除绘制的波形
     */
    public void cleanWaveData() {
        if (data2draw == null)
            return;
        arraycnt = 0;
        for (int i = 0; i < data2draw.length; i++) {
            data2draw[i] = -1f;
        }
        postInvalidate();
    }

}
