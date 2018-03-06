package com.example.asus1.xiyousearch.Presenters

import android.util.Log
import com.example.asus1.xiyousearch.Views.Fragments.ShowDataInter
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response

/**
 * Created by asus1 on 2018/3/6.
 */

class ClassFisrtCall(view:ShowDataInter) :CallBack<ResponseBody>{

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

        if(isFirst){
            mGrades.text = xndList[0]
            mSemester.text = xqdList[0]
            isFirst = false

        }



        var k = 0
        var j = 1


        for (classes in classList){
            if(classes.size == 9 || classes.size == 8){

                if(classes.size == 9){
                    var w = 0
                    for (c in classes){

                        if(w!=1&&w!=0&&c.length>0&&c!=""){

                            mCurriculumViews.addClass(c,mWeeksView.mClassSize/3+20+(mWeeksView.mWeekSize)*(w-2),
                                    80+(mWeeksView.mClassSize*2)*k,
                                    mWeeksView.mClassSize*2-100
                            )

                            Log.d(TAG,k.toString())


                        }

                        w++
                    }
                }else{
                    var w = 0
                    for (c in classes){

                        if(c.length>0&&w!=0&&c!=""){

                            mCurriculumViews.addClass(c,mWeeksView.mClassSize/3+20+(mWeeksView.mWeekSize)*(w-1),
                                    80+(mWeeksView.mClassSize*2)*k,
                                    mWeeksView.mClassSize*2-100
                            )

                            Log.d(TAG,k.toString())

                        }

                        w++
                    }


                }


            }
            if(j%2 == 1)
                k++
            j++
        }
    }
}