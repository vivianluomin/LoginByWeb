package com.example.asus1.xiyousearch.Views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by asus1 on 2018/2/5.
 */

class WeeksView :View{

    private  var mContext:Context?
    private var mPaint:Paint = Paint()
    private var mWidth = 0
    private  var mHeight = 0

    private val mWeeks  = arrayOf("周一","周二","周三","周四","周五","周六","周日")

    private val mClassSize = 100

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr){
        mContext = context

    }



    override fun onDraw(canvas: Canvas?) {
        mPaint.color = Color.parseColor("#27408B")
        mPaint.style = Paint.Style.FILL
        var left = paddingLeft
        var right = paddingRight
        var top = paddingTop
        var weeksize = (mWidth-left-right)/7
        if (canvas!=null){
            canvas.drawLine(left.toFloat(),top.toFloat(),(mWidth-left-right).toFloat(),(top+2).toFloat(),mPaint)

            mPaint.textSize = (24).toFloat()
            mPaint.textAlign = Paint.Align.CENTER
            for (i in 0..6){
                canvas.drawText(mWeeks[i],(mClassSize+weeksize*i).toFloat(),(top+weeksize/3).toFloat(),mPaint)
            }

            canvas.drawLine(left.toFloat(),(top+weeksize/2).toFloat(),(mWidth-left-right).toFloat(),(top+2+weeksize/2).toFloat(),mPaint)


        }

        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var heigthMode  = MeasureSpec.getMode(heightMeasureSpec)


        if(widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize
        }else{
            mWidth = resources.displayMetrics.widthPixels
        }
        if(heigthMode == MeasureSpec.EXACTLY){
            mHeight  = heightSize
        }else{
            mHeight = resources.displayMetrics.heightPixels
        }

        setMeasuredDimension(mWidth,mHeight)

    }





}