package com.comm.util.ui.customview.gcssloop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * http://www.gcssloop.com/customview/Path_Basic/
 */
public class PathView extends View {
    private Paint mPaint;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(DeviceUtil.mWidth / 2, 0);
//        canvas.translate(DeviceUtil.mWidth / 2, DeviceUtil.mHeight / 2);
        /**
         * 第1组: moveTo
         */
//        Path path = new Path();
//        path.moveTo(200, 200);
//        path.lineTo(300, 300);
//        path.lineTo(400,-400);
//        path.lineTo(500,500);
//        path.lineTo(600,600);
//
////        path.close();  //形成封闭的图形
//        canvas.drawPath(path, mPaint);

        /**
         * 第2组: addXxx与arcTo
         */

        Path path = new Path();
        Path src = new Path();
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        path.lineTo(200, 200);                      // lineTo
        path.lineTo(300,400);                         // lineTo
        path.setLastPoint(0,500); //把 (300,400)重置了

//        src.addCircle(0, 0, 100, Path.Direction.CW);
//        path.addPath(src, 0, 200);
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(path, mPaint);

        /**
         * 第三类(addArc与arcTo)
         */
//        canvas.scale(1, -1);
//        Path path = new Path();
//        path.lineTo(100, 100);
//
//        RectF oval = new RectF(0, 00, 300, 300);
//        path.addArc(oval, 0, 270);
//        canvas.drawPath(path, mPaint);
    }
}
