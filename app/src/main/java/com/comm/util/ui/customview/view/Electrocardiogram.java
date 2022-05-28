package com.comm.util.ui.customview.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created on 2017/8/8.
 * 
 */
public class Electrocardiogram extends View{

    private final static String TAG = Electrocardiogram.class.getSimpleName();
    private final Handler mHandler = new Handler(){};

    private final boolean mIsDrawGird = true;                    // 是否画网格
    private final List<Float> datas = new ArrayList<Float>();
    private final List<Float> electrocardDatas = new ArrayList<Float>();
    int horizontalBigGirdNum 	= 6;                       // 横向的线，即纵向大格子的数量，每个大格子里面包含5个小格子
    int verticalBigGirdNum 		= 8;                       // 纵向的线，即横向大格子的数量。
    Runnable runnable;
    private Paint paint;
    private Paint electrocarPaint;                         // 画心电图曲线的画笔
    private Path electrocarPath;                           // 心电图曲线的轨迹 path
    private int width;
    private int height;
    private int baseLine;                                  // 基准线
    private int widthOfSmallGird;                          // 小格子的宽度
    private int maxLevel;
    private int index = 0;

    public Electrocardiogram(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Electrocardiogram(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();							// 画网格的 Paint
        paint.setStyle(Paint.Style.STROKE);

        electrocarPaint = new Paint();					// 画心电图曲线的 Paint
        electrocarPaint.setColor(Color.YELLOW);
        electrocarPaint.setStyle(Paint.Style.STROKE);
        electrocarPaint.setStrokeWidth(5);
        electrocarPath = new Path();					// 心电图的轨迹 Path

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"onMeasure");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG,"onSizeChanged");
        width = w;
        height = h;
        widthOfSmallGird = width / (verticalBigGirdNum * 5); // 小网格的宽度，每个大网格有 5 个小网格
        baseLine = height / 2;				// 基准线位于中间
        maxLevel = height / 3;				// 心电图曲线最大的高度
        setData();							// 设置数据
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
        if( mIsDrawGird ){					// 是否画网格
            drawGird(canvas);				// 画网格
        }
        drawElectrocardiogram(canvas);		// 画心电图
    }

    /**
     * 画心电图曲线
     */
    private void drawElectrocardiogram(Canvas canvas) {
        canvas.drawPath(electrocarPath,electrocarPaint);
        electrocarPath.moveTo(0, baseLine - datas.get(0));
        for( int i = 1; i < electrocardDatas.size(); i++) {
            //  设基准线为0，这里就有一个坐标系转换，一个是左上角为（0,0）的Android View的坐标系
            //  另一个是 基准线的最左侧为 ( 0 , 0 )的坐标系，这条线以上为正数，以下为负数。datas的数据是基于这个坐标系的。
            //  将这个坐标系，转换为Android View 的坐标系。 那就是 Y = y - datas.get(i)
            float y = baseLine - electrocardDatas.get(i);
            electrocarPath.lineTo(i * widthOfSmallGird, y);
        }
        canvas.drawPath(electrocarPath, electrocarPaint);
    }

    /**
     *  增加数据，使心电图呈现由左到右显示出波形的效果
     */
    public void addData(){
        if(datas.size() > 0) {
            // datas 是收集到的数据， electrocardDatas 是显示在屏幕的数据，两者都是 ArrayList<Float>
            electrocardDatas.add(datas.get(index));
            index++;		// 索引增加
            if (index >= datas.size() - 1) {        // 当 datas 的数据用完了。
                index = 0;
                // 清空数据
                electrocardDatas.clear();
                electrocarPath.reset();
                datas.clear();

                // 重新生成数据
                generateElectrocar();
            }
            invalidate();
        }
    }

    public void startDraw(){
        runnable = new Runnable() {
            @Override
            public void run() {
                addData();
                mHandler.postDelayed(runnable,100);		// 100 ms 增加一个数据
            }
        };
        mHandler.post(runnable);
    }


    public void setData(){
        generateElectrocar();
        Log.e(TAG,datas+"");
    }

    // 随机生成的心电图序列 [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 55.05766, -240.32986, 230.08057, -148.4218, 53.343708, -68.89675, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 145.64384, -92.638756, 223.0892, -93.22228, 40.925827, -123.40388, 0.0, 0.0, 0.0, 0.0]


    public void generateElectrocar(){
    	
        for( int i = 0; i < 12  ; i++){         // 前面 12 个小格
            datas.add(0f);						// 为平滑水平的直线
        }
        for( int i = 0; i < 6 ; i++){			// 这里开始陡峭起来
            double random;
            if(i % 2 == 0){						// 一个为正数
                random = Math.random();
            }else{								// 另一个为负数
                random = - Math.random();
            }
            float value = (float) (maxLevel * random);	// 最大值为 maxLevel
            datas.add(value);
        }
        
        // 下面的代码和上面一样

        
        for( int i = 0; i < 12  ; i++){         // 前面 12 个小格
            datas.add(0f);
        }
        for( int i = 0; i < 6 ; i++){
            double random;
            if(i % 2 == 0){
                random = Math.random();
            }else{
                random = - Math.random();
            }
            float value = (float) (maxLevel * random);
            datas.add(value);
        }

        for( int i = 0; i < 6; i++){
            datas.add(0f);
        }
    }

    /**
     * 画网格
     */
    private void drawGird(Canvas canvas) {
        paint.setColor(Color.GREEN);                   // 网格浅绿
        // 横向的网格
        for ( int i = 0; i <= verticalBigGirdNum * 5; i++ ){
            if( i % 5 == 0){       // 每隔 5 个小格，线变粗
                paint.setStrokeWidth(3);
            }else{
                paint.setStrokeWidth(1);
            }
            canvas.drawLine(i * widthOfSmallGird, 0, i * widthOfSmallGird, height, paint);     // 画线
        }

        //  纵向的网格
        for ( int i = 0; i <= horizontalBigGirdNum * 5; i++ ){
            if( i % 5 == 0){	// 每隔 5 个小格，线变粗
                paint.setStrokeWidth(3);
            }else{
                paint.setStrokeWidth(1);
            }
            canvas.drawLine(0, i * widthOfSmallGird, width, i * widthOfSmallGird, paint);
        }
    }
}
