package com.example.asus1.xiyousearch.Views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.asus1.xiyousearch.Module.Info
import com.example.asus1.xiyousearch.R

/**
 * Created by asus1 on 2018/2/12.
 */


class ViewInfoItem:FrameLayout{

    private var mContext:Context?
    private lateinit var mKey:TextView
    private lateinit var mValue:TextView

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr){
        mContext = context
        init()
    }

    private fun  init(){

        var view = View.inflate(mContext,R.layout.layout_info_item,this)

        mKey = view.findViewById(R.id.tv_key)
        mValue = view.findViewById(R.id.tv_value)

    }

    fun setData(item:Info){
        mKey.text = item.key
        mValue.text = item.value
    }
}