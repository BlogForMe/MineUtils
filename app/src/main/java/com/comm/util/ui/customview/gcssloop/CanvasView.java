package com.comm.util.ui.customview.gcssloop;

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
 * http://www.gcssloop.com/customview/Canvas_Convert
 */

public class CanvasView extends View {

    private Context mContext;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    private void initPaint(Context mContext) {

//        mWidth = DisplayUtils.getDialogW((Activity) mContext);
        mHeight = (int) DisplayUtils.getScreenH(mContext);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.FILL); //实心
        mPaint.setStyle(Paint.Style.STROKE); //空心
        mPaint.setStrokeWidth(5f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);
//        canvas.drawPoint(200, 200, mPaint);
//        canvas.drawPoints(new float[]{          //绘制一组点，坐标位置由float数组指定
//                500, 500,
//                500, 600,
//                500, 700
//        }, mPaint);
//
        /**
         * ⑴位移(translate)
         */
        // 在坐标原点绘制一个黑色圆形
//        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
//        // 在坐标原点绘制一个蓝色圆形
//        mPaint.setColor(Color.BLUE);
        canvas.save();
        canvas.translate(200, 200);
        canvas.restore();
        canvas.drawCircle(0, 0, 200, mPaint);


        /**
         * ⑵缩放(scale)
         */
        // 将坐标系原点移动到画布正中心
//        canvas.translate(mWidth / 2, mHeight / 2);
//        RectF rect = new RectF(0, -400, 400, 0);   // 矩形区域
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect, mPaint);
//        canvas.scale(-0.5f, -0.5f);          // 画布缩放  <-- 缩放中心向右偏移了200个单位
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形


        //2、
//        RectF rect = new RectF(-200, -80, 200, 80);
//        canvas.drawRect(rect, mPaint);
//        for (int i = 0; i < 20; i++) {
//            canvas.scale(0.9f, 0.9f);
//            canvas.drawRect(rect, mPaint);
//        }
//        canvas.drawRoundRect(rect, 50, 50, mPaint);

        /**
         * 旋转（rotate）
         */

//        canvas.drawCircle(0, 0, 400, mPaint);
//        canvas.drawCircle(0, 0, 380, mPaint);
//        for (int i = 0; i <= 360; i += 10) {  //绘制两圆之间的连接线
//            canvas.drawLine(0, 380, 0, 400, mPaint);
//            canvas.rotate(10);
//        }
    }
}
