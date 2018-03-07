package com.example.asus1.xiyousearch.Presenters

import com.example.asus1.xiyousearch.Views.Fragments.ClassShowDataInter
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response

/**
 * Created by asus1 on 2018/3/6.
 */

class ClassFisrtCall(view: ClassShowDataInter) :CallBack<ResponseBody>{

    val mView = view

    override fun getResponed(response: Response<ResponseBody>) {
        var xndList = arrayListOf<String>()
        var xqdList = arrayListOf<String>()
        var classList = arrayListOf<ArrayList<String>>()


        var elements = Jsoup.parse(response.body().string())
        var xnd = elements.select("#xnd")[0].getElementsByTag("option")
        for (el in  xnd){
            xndList.add(el.text())

        }
        var xqd = elements.select("#xqd")[0].getElementsByTag("option")

        for (el in  xqd){
            xqdList.add(el.text())

        }

        var table1 = elements.select("#Table1")

        var list = table1.select("tr")


        var i = 0
        for (el in list){
            if(i>1){
                var cl = arrayListOf<String>()
                for (e in el.select("td")){
                    cl.add(e.text())
                }

                classList.add(cl)
            }else{
                i++
            }
        }

        mView.showData(xndList,xqdList,classList)

    }
}