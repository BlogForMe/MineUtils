package com.comm.util.ui.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class MyTextView extends View {
    private Paint textPaint;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(50);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String str = "ABCDEFG";
        canvas.drawText(str, 200, 500, textPaint);

        String str1 = "SLOOP";

    }


}
