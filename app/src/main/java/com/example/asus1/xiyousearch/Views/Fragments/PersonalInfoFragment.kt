package com.example.asus1.xiyousearch.Views.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.asus1.xiyousearch.Views.Activities.InfoAdapter
import com.example.asus1.xiyousearch.Models.Info
import com.example.asus1.xiyousearch.R
import com.example.asus1.xiyousearch.Models.Services.PersonalInfoService
import com.example.asus1.xiyousearch.Presenters.CallBack
import com.example.asus1.xiyousearch.Presenters.InfoCall
import com.example.asus1.xiyousearch.Presenters.URLUtil
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response

/**
 * Created by asus1 on 2018/2/5.
 */

class PersonalInfoFragment:Fragment,InfoShowDataInter{

    private lateinit var mListView:ListView
    private lateinit var mAdater:InfoAdapter
    private var mDataList = arrayListOf<Info>()
    private var callBack = InfoCall(this)

    constructor()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater!!.inflate(R.layout.fragment_personalinfo,container,false)

        mListView = view.findViewById(R.id.list_info)
        mAdater = InfoAdapter(context,R.layout.layout_info_item,mDataList)
        mListView.adapter = mAdater
        getData()

        return view
    }

    private fun getData(){
        var inforCall = URLUtil.instance.mainClient.create(PersonalInfoService::class.java)
                .getInfo(URLUtil.instance.user.name,
                        "����",
                        "N121501",
                        ": http://222.24.62.120/xs_main.aspx?xh="+ URLUtil.instance.user.name
                        )

        URLUtil.instance.doRequest(inforCall,callBack)
    }


    override fun showData(infos: ArrayList<Info>) {
        mDataList.clear()
        mDataList.addAll(infos)
        mAdater.notifyDataSetChanged()

    }


}