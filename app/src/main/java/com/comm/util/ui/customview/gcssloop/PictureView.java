package com.comm.util.ui.customview.gcssloop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.android.util.DisplayUtils;
import com.comm.util.R;


/**
 * http://www.gcssloop.com/customview/Canvas_PictureText
 */
public class PictureView extends View {

    private final int height;
    private final Context mContext;
    //创建Picture
    private final Picture mPicture = new Picture();
    private  int width;

    public PictureView(Context context) {
        this(context, null);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
//        width = DisplayUtils.getDialogW((Activity) context);
        height = (int) DisplayUtils.getScreenH(context);
//        recording();
    }

    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);
        mPicture.endRecording();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 1.使用Picture提供的draw方法绘制:
         */
//        mPicture.draw(canvas);
        /**
         *2.使用Canvas提供的drawPicture方法绘制
         * public void drawPicture (Picture picture, Rect dst)
         */
//        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200));

        /**
         * 将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
         */
//        // 包装成为Drawable
//        PictureDrawable drawable = new PictureDrawable(mPicture);
//        // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
//        drawable.setBounds(0, 0, 250, mPicture.getHeight());
//        //绘制
//        drawable.draw(canvas);

        /**
         *
         (2)drawBitmap
         */
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_customview_01);
        /***
         *  1.
         *  public void drawBitmap (Bitmap bitmap, Matrix matrix, Paint paint)
         */
//        canvas.drawBitmap(bitmap,new Matrix(),new Paint());
        /**
         * 2.public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)
         */
//        canvas.drawBitmap(bitmap, 200, 500, new Paint());

        /**
         * public void drawBitmap (Bitmap bitmap, Rect src, Rect dst, Paint paint)
         public void drawBitmap (Bitmap bitmap, Rect src, RectF dst, Paint paint)
         */

//        canvas.translate(width / 2, height / 2);
        // 指定图片绘制区域(左上角的四分之一)
        Rect src = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);

        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(0, 0, 400, 500);
        //绘制图片
        canvas.drawBitmap(bitmap, src, dst, null);

    }
}
