package com.comm.util.ui.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import com.comm.util.R;

public class EditTextRight extends AppCompatEditText {
    private Paint mPaint;
    private String txtRight;

    public EditTextRight(Context context) {
        super(context);
    }

    public EditTextRight(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttrs(context, attrs);

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EditTextRight);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        txtRight = ta.getString(R.styleable.EditTextRight_textright);
//        int color = ta.getColor(R.styleable.EditTextRight_font, Color.BLACK);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float txtSize = getTextSize();
        if (!TextUtils.isEmpty(txtRight)) {
            float yrig = getWidth() - txtSize * txtRight.length() - 10; //getWidth() 控件宽度
//            Timber.d("txt " + txtRight + "   getRight " + yrig);
            canvas.drawText(txtRight, yrig, getBaseline(), getPaint());
        }
    }

}