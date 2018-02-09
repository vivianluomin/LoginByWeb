package com.example.asus1.xiyousearch.Views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
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


    val mClassSize = 300
    var mWeekSize = 0

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
        var weeksize = (mWidth-left-right-mClassSize/3)/7
        mWeekSize = weeksize
        if (canvas!=null){
            canvas.drawLine(left.toFloat(),top.toFloat(),(mWidth-left-right).toFloat(),(top+2).toFloat(),mPaint)

            mPaint.textSize = (24).toFloat()
            mPaint.textAlign = Paint.Align.LEFT
            for (i in 0..6){
                canvas.drawText(mWeeks[i],(mClassSize/3+30+weeksize*i).toFloat(),(top+weeksize/3).toFloat(),mPaint)
            }

            canvas.drawLine(left.toFloat(),(top+weeksize/2).toFloat(),(mWidth-left-right).toFloat(),(top+weeksize/2).toFloat(),mPaint)

            canvas.drawLine((left+mClassSize/3).toFloat()
                    ,top.toFloat(),(left+mClassSize/3).toFloat(),mHeight.toFloat(),mPaint)

            mPaint.textAlign = Paint.Align.CENTER
            for (i in 0..11){

                canvas.drawText((i+1).toString()
                        ,(left+20).toFloat(),(top+weeksize+5+mClassSize*i).toFloat(),mPaint)
                Log.d("class",i.toString())
                if(i!=11){
                    canvas.drawLine(left.toFloat(),(top+weeksize+mClassSize*i+mClassSize/2).toFloat()
                            ,(mWidth-left-right).toFloat(),(top+weeksize+mClassSize*i+mClassSize/2).toFloat(),mPaint)
                }
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
            mWidth = resources.displayMetrics.widthPixels
        }
        if(heigthMode == MeasureSpec.EXACTLY){
            mHeight  = heightSize
        }else{
            mHeight = resources.displayMetrics.heightPixels*2
        }

        setMeasuredDimension(mWidth,mHeight)

    }





}