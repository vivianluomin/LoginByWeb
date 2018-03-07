package com.example.asus1.xiyousearch.Presenters

import android.util.Log
import com.example.asus1.xiyousearch.Views.Fragments.GradeShowDataInter
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response

/**
 * Created by asus1 on 2018/3/7.
 */

class GradeXCall(view:GradeShowDataInter):CallBack<ResponseBody>{

    private val mView = view
    override fun getResponed(response: Response<ResponseBody>) {
        var element = Jsoup.parse(response.body().string())

        var xnd = element.select("#ddlXN")

        var xndList = arrayListOf<String>()
        var xqdList = arrayListOf<String>()

        for (el in xnd.text().split(" ")){
            if (el!=""){
                xndList.add(el)
                Log.d("gradesFrag",el)
            }
        }

        var xqd = element.select("#ddlXQ").text().split(" ")
        println(xqd)

        for (el in xqd){

            if(el!=""){
                xqdList.add(el)
                Log.d("gradesFrag",el)
            }
        }

        mView.showXData(xndList,xqdList)

    }

}