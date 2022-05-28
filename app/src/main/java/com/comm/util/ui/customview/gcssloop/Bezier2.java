package com.comm.util.ui.customview.gcssloop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.android.util.DisplayUtils;

/**
 * http://www.gcssloop.com/customview/Path_Bezier
 */
public class Bezier2 extends View {
    private final Paint mPaint;
    private final PointF start, end;
    private final PointF control1, control2;
    private final int centerX;
    private final int centerY;
    private boolean mode = true;

    public Bezier2(Context context) {
        this(context, null);
    }

    public Bezier2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control1 = new PointF(0, 0);
        control2 = new PointF(0, 0);

        centerX = (int) (DisplayUtils.getScreenW(context) / 2);
        centerY = (int) (DisplayUtils.getScreenH(context) / 2);

    }


    /**
     * 移动不同的点
     *
     * @param mode
     */
    public void setMOde(boolean mode) {
        this.mode = mode;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control1.x = centerX;
        control1.y = centerY - 100;
        control2.x = centerX;
        control2.y = centerY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 根据触摸位置更新控制点，并提示重绘
        if (mode) {
            control1.x = event.getX();
            control1.y = event.getY();
        } else {
            control2.x = event.getX();
            control2.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y, control2.x, control2.y, mPaint);
        canvas.drawLine(control2.x, control2.y, end.x, end.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();

        path.moveTo(start.x, start.y);
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, end.x, end.y);
        canvas.drawPath(path, mPaint);
    }
}
