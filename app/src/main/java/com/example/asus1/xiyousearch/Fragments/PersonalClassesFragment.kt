package com.example.asus1.xiyousearch.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.asus1.xiyousearch.R
import com.example.asus1.xiyousearch.Services.PersonalClassService
import com.example.asus1.xiyousearch.URLUtil
import com.example.asus1.xiyousearch.Views.CurriculumViews
import com.example.asus1.xiyousearch.Views.WeeksView
import kotlinx.android.synthetic.main.fragment_personalclasses.view.*
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import retrofit2.Response

/**
 * Created by asus1 on 2018/2/5.
 */

class PersonalClassesFragment :Fragment,View.OnClickListener{

    private lateinit var mCurriculumViews: CurriculumViews
    private lateinit var mWeeksView:WeeksView
    private lateinit var mGrades : TextView
    private lateinit var mSemester:TextView

    private var xndList = arrayListOf<String>()
    private var xqdList = arrayListOf<String>()

    private var classList = arrayListOf<ArrayList<String>>()

    private val TAG = "ClassFragment"


    constructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater!!.inflate(R.layout.fragment_personalclasses,container,false)

        mCurriculumViews = view.findViewById(R.id.curriculum)
        mWeeksView = view.findViewById(R.id.weeks)
        mGrades = view.findViewById(R.id.tv_grades)
        mGrades.setOnClickListener(this)
        mSemester = view.findViewById(R.id.tv_semester)
        mSemester.setOnClickListener(this)

        getData()



        return view
    }

    private fun getData(){
        var classesService = URLUtil.mainClient.
                create(PersonalClassService::class.java)
                .getClasses(URLUtil.user.name,
                        "%C2%DE%C3%F4",
                        "N121603",
                        "http://222.24.62.120/xskbcx.aspx?xh="+URLUtil.user.name+"&xm=%25C2%25DE%25C3%25F4&gnmkdm=N121603")
        URLUtil.doRequest(classesService,callBack)

    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_grades->{

            }

            R.id.tv_semester->{

            }
        }
    }

    val callBack = object :URLUtil.CallBack<ResponseBody>{

        override fun getResponed(response: Response<ResponseBody>) {

            var elements = Jsoup.parse(response.body().string())


            var xnd = elements.select("#xnd")[0].getElementsByTag("option")
            for (el in  xnd){
                xndList.add(el.text())
                Log.d(TAG,el.text())
            }
            var xqd = elements.select("#xqd")[0].getElementsByTag("option")

            for (el in  xqd){
                xqdList.add(el.text())
                Log.d(TAG,el.text())
            }

            var table1 = elements.select("#Table1")

           var list = table1.select("tr")


            var i = 0
            for (el in list){
                if(i>1){
                    var cl = arrayListOf<String>()
                    for (e in el.select("td")){
                        cl.add(e.text())
                        Log.d(TAG,"eee: "+e.text())
                    }

                    classList.add(cl)
                    Log.d(TAG,"------")
                }else{
                    i++
                }
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


}