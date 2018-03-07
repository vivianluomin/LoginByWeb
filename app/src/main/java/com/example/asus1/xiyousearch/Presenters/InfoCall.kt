package com.example.asus1.xiyousearch.Presenters

import com.example.asus1.xiyousearch.Models.Info
import com.example.asus1.xiyousearch.Views.Fragments.InfoShowDataInter
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response

/**
 * Created by asus1 on 2018/3/7.
 */

class InfoCall(view:InfoShowDataInter) :CallBack<ResponseBody>{
    private val mView = view
    override fun getResponed(response: Response<ResponseBody>) {
        var element = Jsoup.parse(response.body().string())

        var mDataList = arrayListOf<Info>()

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

        mView.showData(mDataList)

    }
}