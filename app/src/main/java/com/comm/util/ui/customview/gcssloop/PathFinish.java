package com.comm.util.ui.customview.gcssloop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class PathFinish extends View {
    private final Paint mPaint;

    public PathFinish(Context context) {
        this(context, null);
    }

    public PathFinish(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        Path path = new Path();
//        path.moveTo(100, 100);
//        path.rLineTo(100, 200);
//        canvas.drawPath(path, mPaint);
//        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth() / 2, mPaint);

        RectF rectF = new RectF(0, 0, 200, 200);
        canvas.drawRect(rectF, mPaint);
    }
}
