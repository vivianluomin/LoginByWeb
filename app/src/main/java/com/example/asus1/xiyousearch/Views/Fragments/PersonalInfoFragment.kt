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
import com.example.asus1.xiyousearch.Presenters.URLUtil
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response

/**
 * Created by asus1 on 2018/2/5.
 */

class PersonalInfoFragment:Fragment{

    private lateinit var mListView:ListView
    private lateinit var mAdater:InfoAdapter
    private var mDataList = arrayListOf<Info>()

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

    private var callBack = object : CallBack<ResponseBody>{
        override fun getResponed(response: Response<ResponseBody>) {

            var element = Jsoup.parse(response.body().string())
            mDataList.clear()

            mDataList.add(Info(element.select("#lbxsgrxx_xh").text(),
                    element.select("#xh").text()
            ))

            mDataList.add(Info(element.select("#lbxsgrxx_xm").text(),
                    element.select("#xm").text()
            ))
            mDataList.add(Info(element.select("#lbxsgrxx_xb").text(),
                    element.select("#lbl_xb").text()
            ))
            mDataList.add(Info(element.select("#lbxsgrxx_csrq").text(),
                    element.select("#lbl_csrq").text()
            ))
            mDataList.add(Info(element.select("#lbxsgrxx_mz").text(),
                    element.select("#lbl_mz").text()
            ))
            mDataList.add(Info(element.select("#lbxsgrxx_zzmm").text(),
                    element.select("#lbl_zzmm").text()
            ))

            mDataList.add(Info(element.select("#lbxsgrxx_lys").text(),
                    element.select("#lbl_lys").text()
            ))
            mDataList.add(Info(element.select("#lbxsgrxx_sfzh").text(),
                    element.select("#lbl_sfzh").text()
            ))
            mDataList.add(Info(element.select("#lbxsgrxx_xlcc").text(),
                    element.select("#lbl_CC").text()
            ))
            mDataList.add(Info(element.select("#lbxsgrxx_xy").text(),
                    element.select("#lbl_xy").text()
            ))
            mDataList.add(Info(element.select("#lbxsgrxx_zymc").text(),
                    element.select("#lbl_zymc").text()
            ))
            mDataList.add(Info(element.select("#lbxsgrxx_yydj").text(),
                    element.select("#lbl_yydj").text()
            ))


            mDataList.add(Info(element.select("#lbxsgrxx_xzb").text(),
                    element.select("#lbl_xzb").text()
            ))

            mDataList.add(Info(element.select("#lbxsgrxx_xz").text(),
                    element.select("#lbl_xz").text()
            ))

            mDataList.add(Info(element.select("#lbxsgrxx_dqszj").text(),
                    element.select("#lbl_dqszj").text()
            ))

            mDataList.add(Info(element.select("#lbxsgrxx_ksh").text(),
                    element.select("#lbl_ksh").text()
            ))


            mAdater.notifyDataSetChanged()

        }

    }
}