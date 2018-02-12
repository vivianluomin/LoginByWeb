package com.example.asus1.xiyousearch.Activities

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.asus1.xiyousearch.Module.Info
import com.example.asus1.xiyousearch.Views.ViewInfoItem

/**
 * Created by asus1 on 2018/2/12.
 */

class InfoAdapter:ArrayAdapter<Info>{

    private var mContext:Context?
    private var mResource = 0
    private var mList:ArrayList<out Info>?

    constructor(context: Context?, resource: Int, objects: ArrayList<out Info>?)
            : super(context, resource, objects){

        mContext = context
        mResource = resource
        mList = objects
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view:ViewInfoItem
        if(convertView == null){
            view  = ViewInfoItem(mContext)
        }else{
            view = convertView as ViewInfoItem
        }

        view.setData(mList!![position])


        return view
    }

}