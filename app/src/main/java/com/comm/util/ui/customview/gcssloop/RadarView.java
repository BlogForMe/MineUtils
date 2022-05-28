package com.comm.util.ui.customview.gcssloop;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.android.util.DisplayUtils;
import timber.log.Timber;


/**
 * 自定义雷达控件
 * http://www.gcssloop.com/customview/Path_Basic/
 * https://www.jianshu.com/p/afe7bfe7a3ee
 */
public class RadarView extends View {

    //边的条数
    private final int count = 6;
    private final int centerX;
    private final int centerY ;
    private float radius;     //网格最大半径
    private Paint mainPaint;   //雷达区画笔
    private Paint textPaint;    //文本画笔
    private Paint valuePaint;    //数据区画笔
    //标题文字
    private LinkedList<String> titles;
    private LinkedList<Double> mixData;
    private float angle; //均分的角度


    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        centerX = (int) (DisplayUtils.getScreenW(context) / 2);
        centerY = (int) (DisplayUtils.getScreenH(context) / 2);
        initEdges();
    }

    private void initEdges() {
        mainPaint = new Paint();
        mainPaint.setColor(Color.BLACK);
        mainPaint.setAntiAlias(true);
        mainPaint.setStrokeWidth(1);
        mainPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);
        textPaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);

        valuePaint = new Paint();
        valuePaint.setColor(Color.RED);
        valuePaint.setAntiAlias(true);
        valuePaint.setStyle(Paint.Style.FILL);

        titles = new LinkedList<>();
        titles.add("JAVA");
        titles.add("C++");
        titles.add("数据库");
        titles.add("算法");
        titles.add("Android");
        titles.add("Python");

        mixData = new LinkedList<>();
        mixData.add(60.0);
        mixData.add(100.0);
        mixData.add(45.0);
        mixData.add(85.0);
        mixData.add(99.0);
        mixData.add(66.0);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2 * 0.7f;

        //一旦size发生改变，重新绘制
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);

        drawTitle(canvas);
    }


    /**
     * 画六边形
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        //1度=1*PI/180   360度=2*PI   那么我们每旋转一次的角度为2*PI/内角个数
        //中心与相邻两个内角相连的夹角角度
        angle = (float) (2 * Math.PI / count);
        //每个蛛丝之间的间距
        float r = radius / (count - 1);
        for (int i = 0; i < count; i++) {
//            //当前半径
            float curR = r * i;
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY); //设置起始点
                } else {
                    //对于直角三角形sin(x)是对边比斜边，cos(x)是底边比斜边，tan(x)是对边比底边
                    //因此可以推导出:底边(x坐标)=斜边(半径)*cos(夹角角度)
                    //               对边(y坐标)=斜边(半径)*sin(夹角角度)

                    double cosx = curR * Math.cos(angle * j);
                    double cosy = curR * Math.sin(angle * j);
                    float x = (float) (centerX + cosx);
                    float y = (float) (centerY + cosy);

                    Timber.d("xy " + x + "  " + y + " cosx cosy " + cosx + " " + cosy);
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制原点到六个顶点的直线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }


    /**
     * 绘制标题文字
     *
     * @param canvas
     */
    private void drawTitle(Canvas canvas) {
        if (count != titles.size()) {
            return;
        }
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        //绘制文字时不让文字和雷达图形交叉,加大绘制半径
        float textRadius = radius + fontHeight;
        Timber.d("textRadius  " + textRadius);
        double pi = Math.PI;
        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + textRadius * Math.cos(angle * i));
            float y = (float) (centerY + textRadius * Math.cos(angle * i));
            //当前绘制标题所在顶点角度
            float degrees = angle * i;
            //从右下角开始顺时针画起,与真实坐标系相反
            if (degrees >= 0 && degrees < pi / 2) { //第四象限
                float dis = textPaint.measureText(titles.get(i)) / (titles.get(i).length() - 1);
                canvas.drawText(titles.get(i), x + dis, y, textPaint);
            } else if (degrees >= pi / 2 && degrees < pi) {//第三象限
                float dis = textPaint.measureText(titles.get(i)) / (titles.get(i).length() - 1);
                canvas.drawText(titles.get(i), x + dis, y, textPaint);
            } else if (degrees >= pi && degrees < 3 * pi / 2) {//第三象限
                float dis = textPaint.measureText(titles.get(i)) / (titles.get(i).length() - 1);
                canvas.drawText(titles.get(i), x + dis, y, textPaint);
            } else if (degrees >= 3 * pi / 2 && degrees < 2 * pi) {//第三象限
                canvas.drawText(titles.get(i), x, y, textPaint);
            }
        }

    }


}
