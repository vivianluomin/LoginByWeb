package com.example.asus1.xiyousearch.Views

import android.content.Context
import android.util.AttributeSet
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


    fun addClass(content:String,left:Int,top:Int,right:Int,bottom:Int){

       var view = TextView(mContext)
       view.layout(left,top,right,bottom)
        view.setTextColor(resources.getColor(R.color.whlit))
        view.setBackgroundResource(mColorBgs[((Math.random()*10)%5).toInt()])
        view.setText(content)

        addView(view)


    }


}