package com.comm.util.ui.surview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * 画板
 */
class DrawingBoardView : SurfaceView, SurfaceHolder.Callback, Runnable {
    private var mCanvas: Canvas? = null
    private var mSurfaceHolder: SurfaceHolder? = holder

    //子线程标志位
    private var mIsDrawing = false
    private val x = 0
    private var y: Int = 0
    private var mPaint: Paint? = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 5f
    }
    private var mPath: Path? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        mSurfaceHolder?.addCallback(this)
        isFocusable = true
        keepScreenOn = true
        isFocusableInTouchMode = true

        mPath = Path()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mIsDrawing = true
        //开启子线程
        Thread(this).start()
    }

    override fun run() {
        while (mIsDrawing) {
            draw()
        }
    }

    private fun draw() {
        try {
            mCanvas = mSurfaceHolder?.lockCanvas()
            //SurfaceView背景
            mCanvas?.drawColor(Color.WHITE)
            mPaint?.let { mPath?.let { it1 -> mCanvas?.drawPath(it1, it) } }
        } catch (e: Exception) {

        } finally {
            mCanvas?.let {
                mSurfaceHolder?.unlockCanvasAndPost(it)
            }
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> x?.let {
                if (y != null) {
                    mPath?.moveTo(it, y)
                }
            }

            MotionEvent.ACTION_MOVE -> x?.let {
                if (y != null) {
                    mPath?.lineTo(it, y)
                }
            }
        }
        return true
    }

}