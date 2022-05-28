package com.comm.util.ui.customview.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.comm.util.R;

/**
 * Created by A on 2018/3/1.
 */

public class LinearRoomConfig extends LinearLayout {
    public LinearRoomConfig(Context context) {
        this(context, null);
    }

    public LinearRoomConfig(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearRoomConfig(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LinearRoomConfig(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {

//        int size = 15;
        LinearLayout llParent1 = new LinearLayout(context);
        LinearLayout.LayoutParams lp1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        llParent1.setLayoutParams(lp1);
        llParent1.setOrientation(VERTICAL);

        for (int j=0;j<2;j++){
            LinearLayout llParent11 = new LinearLayout(context);
            llParent11.setLayoutParams(lp1);
            LayoutParams tvLp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
            for (int i = 0; i < 6; i++) {
                TextView textView = new TextView(context);
                textView.setText("测试");
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setLayoutParams(tvLp);
                textView.setCompoundDrawablePadding(10);
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_bed);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable, null, null);
                llParent11.addView(textView);
            }
            llParent1.addView(llParent11,j);
        }
//        llParent1.addView(llParent11, 0);
//        TextView textView = new TextView(context);
//        textView.setText("呵呵");
//        llParent1.addView(textView, 1);
        addView(llParent1);
    }

}
