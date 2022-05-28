package com.comm.util.ui.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import timber.log.Timber;

import static com.comm.util.ui.customview.view.StaticReceive.mECGReplayBuffer;


public class DrawThreadCradio extends BaseDraw {
    /**
     * 接收到蓝牙消息——PC80B发送文件数据
     */
    public static final int MSG_80B_FILE = 0x201;
    /**
     * 接收到蓝牙消息——PC80B发送实时波形数据
     */
    public static final int MSG_80B_WAVE = 0x202;

    /**
     * 当前view的高度 (mm)
     */
    private final float heightMm = 0;
    /**
     * 心电波形高度缩放比例
     */
    private final float zoomECGforMm = 0.0f;
    private final String TAG = getClass().getSimpleName();
    private final CornerPathEffect cornerPathEffect = new CornerPathEffect(20);
    /**
     * 当前波形增益
     */
    protected int gain = 2;
    int horizontalBigGirdNum = 8;                       // 横向的线，即纵向大格子的数量，每个大格子里面包含5个小格子
    int verticalBigGirdNum = 25;                       // 纵向的线，即横向大格子的数量。
    private int widthOfSmallGird;
    private int width,height,baseLine;
    private int maxLevel;

    public DrawThreadCradio(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawThreadCradio(Context context) {
        super(context);
    }

    public DrawThreadCradio(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 恢复读取数据线程
     */
    public synchronized void Continue() {
        this.pause = false;
        StaticReceive.DRAWDATA.clear();
        this.notify();
    }

    @Override
    public void run() {
        super.run();
        synchronized (this) {
            StaticReceive.DRAWDATA.clear();
            while (!stop) {
                try {
                    if (pause) {
                        this.wait();
                    }
                    if (StaticReceive.DRAWDATA.size() > 0) {
                        Integer data = StaticReceive.DRAWDATA.remove(0);
                        addData(data);
//                        if (data.flag == 1) {
//                            mHandler.sendEmptyMessage(StaticReceive.MSG_DATA_PULSE);
//                        }
                        if (StaticReceive.DRAWDATA.size() > 20) {
                            Thread.sleep(5);
                        } else {
                            Thread.sleep(10);
                        }
                    } else if (mECGReplayBuffer != null && mECGReplayBuffer.size() > 0) {
                        float y = mECGReplayBuffer.remove(0);
                        Thread.sleep(20);//设置播放速度,回放不需要搏动标记,limit the speed of replaying
                        addData(y);

                    } else {
                        Thread.sleep(100);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            StaticReceive.DRAWDATA.clear();
            cleanWaveData();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure");
        width = getMeasuredWidth();
        widthOfSmallGird = width / (verticalBigGirdNum * 5); // 小网格的宽度，每个大网格有 5 个小网格
        height = widthOfSmallGird*5*8;
        setMeasuredDimension(width,height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            return;
        }
        paint.setPathEffect(cornerPathEffect);
//        paint.setColor(Color.RED);
        paint.setColor(Color.parseColor("#23BDC5"));
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(dm.density * 2);
        Path path = new Path();
        path.moveTo(0, getYPx(data2draw[0]));
        for (int i = 0; i < data2draw.length; i++) {
            float yd = getYPx(data2draw[i]);
            path.lineTo(i * stepx, yd);
            Timber.i("onDraw()   "+ "X   " + i * stepx + " Y   " + getYPx(data2draw[i]) + "  yd  " + yd);
        }
        canvas.drawPath(path, paint);
//        paint.setColor(Color.WHITE);
//        paint.setStrokeWidth(5);
//		canvas.drawLine(arraycnt * stepx - 1, 0, arraycnt * stepx - 1, height,
//				paint);
    }

//    private float getYPx(int data) {
//        return (185 - (data - 2048) * gain + 2048) * 185.0f / 4096.0f;
//    }

    public float getYPx(float data) {
        if (baseLine < Math.abs(data * 10 * 5)) {
            if (data < 0) {
                return -2 * baseLine;
            } else {
                return 2 * baseLine;
            }
        }
        return baseLine - data * 10 * 5;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        baseLine = height / 2;                // 基准线位于中间
        maxLevel = height / 3;                // 心电图曲线最大的高度
        stepx = dm.density;
        height = h;
        if (isInEditMode()) {
            return;
        }
        data2draw = new Float[(int)(w / stepx)];
        for (int i = 0; i < data2draw.length; i++) {
            data2draw[i] = -1f;
        }

        Log.i("onSizeChanged", "heightMm" + heightMm + " zoomECGforMm " + zoomECGforMm);
    }


    /**
     * 波形增益
     * 设置
     *
     * @param gain
     */
    public void setGain(int gain) {
        this.gain = gain == 0 ? 2 : gain;
    }


}
