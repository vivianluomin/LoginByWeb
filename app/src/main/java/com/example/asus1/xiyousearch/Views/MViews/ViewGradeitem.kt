package com.example.asus1.xiyousearch.Views.MViews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.asus1.xiyousearch.Models.Grades
import com.example.asus1.xiyousearch.R

/**
 * Created by asus1 on 2018/2/11.
 */


class ViewGradeitem:FrameLayout{

    private var mContext:Context?

    private lateinit var mClassName:TextView
    private lateinit var mClassType:TextView
    private lateinit var mCredit:TextView
    private lateinit var mGradePoint:TextView
    private lateinit var mMarks :TextView

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr){
        mContext = context
        init()
    }

    private fun init(){
        var view = View.inflate(mContext,R.layout.layout_grade_item,this)
        mClassName = view.findViewById(R.id.tv_class_name)
        mClassType = view.findViewById(R.id.tv_class_type)
        mCredit = view.findViewById(R.id.tv_credit)
        mGradePoint = view.findViewById(R.id.tv_grade_point)
        mMarks = view.findViewById(R.id.tv_marks)
    }

    fun setData(grades: Grades){
        mClassName.text = grades.className
        mClassType.text = grades.classType
        mCredit.text = grades.credit
        mGradePoint.text= grades.gradePoint
        mMarks.text = grades.marks


    }
}