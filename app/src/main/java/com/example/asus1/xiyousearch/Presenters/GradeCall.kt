package com.example.asus1.xiyousearch.Presenters

import android.util.Log
import com.example.asus1.xiyousearch.Models.Grades
import com.example.asus1.xiyousearch.Views.Fragments.GradeShowDataInter
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response

/**
 * Created by asus1 on 2018/3/7.
 */

class GradeCall(vieww:GradeShowDataInter) :CallBack<ResponseBody>{

    val  mView = vieww

    override fun getResponed(response: Response<ResponseBody>) {
        var element = Jsoup.parse(response.body().string())

        var table = element.select("#Datagrid1")
        var list = table.select("tr")

        var i = 0
        var dataList = arrayListOf<Grades>()
        for (el in list){
            if(i>1){
                var cl = arrayListOf<String>()
                for (e in el.select("td")){
                    cl.add(e.text())
                    Log.d("GradeFragment",e.text())
                }

                var grades = Grades(cl[3], cl[4], cl[6], cl[7], cl[8])
                dataList.add(grades)

            }else{
                i++
            }
        }

        mView.showGradeData(dataList)
    }
}