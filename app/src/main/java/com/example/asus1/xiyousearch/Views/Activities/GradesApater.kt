package com.example.asus1.xiyousearch.Views.Activities

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.asus1.xiyousearch.Models.Grades
import com.example.asus1.xiyousearch.Views.MViews.ViewGradeitem

/**
 * Created by asus1 on 2018/2/11.
 */

class GradesApater:ArrayAdapter<Grades>{

    private  var mContext: Context?
    private  var mResource: Int = 0
    private  var dataList: ArrayList<out Grades>?


    constructor(context: Context?, resource: Int, objects: ArrayList<out Grades>?)
            : super(context, resource, objects){

        mContext = context
        mResource = resource
        dataList = objects

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view:ViewGradeitem
        if(convertView == null){
            view = ViewGradeitem(mContext)
        }else{
            view = convertView as  ViewGradeitem
        }

        view.setData(dataList!![position])

        return view
    }

}