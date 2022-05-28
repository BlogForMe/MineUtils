package com.comm.util.ui.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.android.util.DisplayUtils;

/**
 * Created by jon on 3/3/18.
 * 自定义TextView  ,均份LinearLayout
 */

public class LinearTextView extends View {
    private final Paint mPaint;
    private final int xwidth;

    public LinearTextView(Context context) {
        this(context, null);
    }

    public LinearTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(40f);
        xwidth = (int) DisplayUtils.getScreenW(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        String txttext = "你好";
        float stringWidth = mPaint.measureText(txttext);
        int tWidth = xwidth / 12;

        int k = 12;
        for (int i = 1; i < k; i++) {
            if (i % 2 == 0) {
                continue;
            }
            canvas.drawText(txttext, xwidth * i / k - stringWidth / 2, 50, mPaint);
        }
    }

}
