package com.example.asus1.xiyousearch.Views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.example.asus1.xiyousearch.R

/**
 * Created by asus1 on 2018/2/9.
 */

class CurriculumViews :FrameLayout{

    private var mContext: Context?

    private val mColorBgs = arrayOf(R.drawable.bg_class_1,
            R.drawable.bg_class_2,
            R.drawable.bg_class_3,
            R.drawable.bg_class_4,
            R.drawable.bg_class_5)

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        mContext = context

    }


    fun addClass(content:String,left:Int,top:Int,height:Int){

       var view = TextView(mContext)
        view.textSize = (12).toFloat()


        var params = FrameLayout.LayoutParams(100,height)
        params.leftMargin = left
        params.topMargin = top


        view.layoutParams = params

        view.setTextColor(resources.getColor(R.color.whlit))
        view.setBackgroundResource(mColorBgs[((Math.random()*10)%5).toInt()])
        view.setText(content)
        view.gravity = View.TEXT_ALIGNMENT_CENTER

        addView(view)


    }


}