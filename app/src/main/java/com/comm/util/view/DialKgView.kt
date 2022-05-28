package com.comm.util.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.comm.util.R
import timber.log.Timber
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


class DialKgView @JvmOverloads constructor(context: Context?, attrs: AttributeSet?,
                                           defStyleAttr: Int=0):View(context, attrs, defStyleAttr){

    val mDialPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mCoordPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mSrcPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)


    private var ebelkg: Float
    private var mRadius: Float

    private var arcWidth: Float = 1.0f
    private var srcWidth: Float = 120f

    init {
        val a = context?.theme?.obtainStyledAttributes(attrs, R.styleable.DialKgView, 0, 0)
        try {
            mRadius = a!!.getFloat(R.styleable.DialKgView_dial_radius, 70f)
            ebelkg = a.getFloat(R.styleable.DialKgView_ebel_kg, 0f)
        } finally {
            a?.recycle()
        }


        mDialPaint.apply {
            color = Color.parseColor("#A2CB79")
            strokeWidth = arcWidth*3
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            textSize = 28f
        }

        mLinePaint.apply {
            color = Color.parseColor("#DDDDDD")
            strokeWidth = arcWidth
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
        mCoordPaint.apply {
            color = Color.parseColor("#666666")
            strokeWidth = arcWidth
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
        mSrcPaint.apply {
            color = Color.parseColor("#F8F9F6")
            strokeWidth = srcWidth
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
        mTextPaint.apply {
            color = Color.parseColor("#333333")
            strokeWidth = arcWidth*120
            textSize = 80f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val centerX = width / 2.0f
        val centerY = height / 2.0f

        val rectF = RectF()
        rectF.left = arcWidth
        rectF.top = arcWidth
        rectF.right = centerX * 2 - arcWidth
        rectF.bottom = centerY * 2 - arcWidth

        var mRadius = (rectF.right -rectF.left)/ 2.0f
//        var mRadius = 300



//        canvas?.drawArc(rectF,45f,-270f,false,mDialPaint)
//        canvas?.drawArc(rectF,130f,275f,false,mDialPaint) //extra 5 src

        canvas?.drawArc(rectF,130f,280f,false,mDialPaint) //extra 5 src

        val rectsrcF = RectF()
        rectsrcF.left = srcWidth
        rectsrcF.top = srcWidth
        rectsrcF.right = centerX * 2 - srcWidth
        rectsrcF.bottom = centerY * 2 - srcWidth

        canvas?.drawArc(rectsrcF,0f,360f,false,mSrcPaint)   //  INNER SRC

        Timber.i("mRadius  $mRadius")
        canvas?.translate(centerX,centerY)

//        canvas?.drawLine(0f,0f,500f,0f,mLinePaint)
//        canvas?.drawLine(0f,0f,0f,500f,mLinePaint)

        var  trangel=135.0
        var line = 0

        mDialPaint.strokeWidth = arcWidth*2

        //划线
        val kgRect = Rect()
        do {
            var angle = (trangel* PI)/180
            var startX = cos(angle).toFloat() * (mRadius-10)
            var startY = sin(angle).toFloat() * (mRadius-10)
            var endx = cos(angle).toFloat() * (mRadius -35)
            var endY = sin(angle).toFloat() * (mRadius -35)
            Timber.i("startx  $startX  startY $startY   endx $endx  endY $endY")
            Timber.i("trangel  $trangel" )

            if (line%4==0){
                canvas?.drawLine(startX, startY,endx,endY
                        ,mDialPaint)
                var kgText = (line * 5).toString()
                mDialPaint.getTextBounds(kgText,0,kgText.length,kgRect)
                var txtHeight = kgRect.height()
                var txtWidth = kgRect.width()
                canvas?.drawText(kgText,cos(angle).toFloat() * (mRadius-70)-txtWidth/2,sin(angle).toFloat() * (mRadius-70)+txtHeight/2,mDialPaint)

            }else{
                canvas?.drawLine(startX, startY,endx,endY
                        ,mLinePaint)
            }
            trangel+=7.5

            line++
        }while (trangel<=405)


        try {
            val iss = context.assets.open("ble_kg_point.png")
            var pBitmap = BitmapFactory.decodeStream(iss)
            iss.close()

//            canvas?.drawBitmap(pBitmap,cos(240f)* (mRadius-150),sin(240f)* (mRadius-150),null)
            canvas?.save()
//            canvas?.rotate(90.0f-2.0f)
            var kgdegree = 1.5f * ebelkg + 225
            canvas?.rotate(kgdegree-2.0f)
            canvas?.drawBitmap(pBitmap,0f,-220f,null)

            canvas?.restore()


        }catch (e:Exception){
            e.printStackTrace()
        }
        var kgVrect = measureTextSize(ebelkg.toString(), mTextPaint)
        var kgvx = -kgVrect.width() / 2f

        canvas?.drawText(ebelkg.toString(),kgvx,0f,mTextPaint)



        var kgtrect = measureTextSize(ebelkg.toString(), mTextPaint)

        canvas?.drawText("kg",-kgtrect.width() / 2f+10,kgtrect.height()/2f+60,mTextPaint)

    }


    fun  measureTextSize(txt:String,mPaint:Paint):Rect{
        val kgRect = Rect()
        mPaint.getTextBounds(txt,0,txt.length,kgRect)
        return  kgRect

    }

    fun setEbelKg(mKg:Float){
        ebelkg = mKg
        postInvalidate()
    }
}