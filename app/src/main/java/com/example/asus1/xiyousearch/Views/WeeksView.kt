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

    private val mWeeks  = Array<String>(5){"星期一";"星期二";"星期三";"星期四";"星期五"}


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
        var textsize = (mWidth-left-right)/5
        if (canvas!=null){
            canvas.drawLine(left.toFloat(),top.toFloat(),(mWidth-left-right).toFloat(),(top+2).toFloat(),mPaint)
            for (i in 0..4){

            }
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
            mWidth = 300
        }
        if(heigthMode == MeasureSpec.EXACTLY){
            mHeight  = heightSize
        }else{
            mHeight = 300
        }

        setMeasuredDimension(mWidth,mHeight)

    }





}