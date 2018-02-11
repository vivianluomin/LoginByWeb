package com.example.asus1.xiyousearch.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.TextView
import com.example.asus1.xiyousearch.Activities.GradesApater
import com.example.asus1.xiyousearch.Grades
import com.example.asus1.xiyousearch.R
import com.example.asus1.xiyousearch.Services.PersonalClassService
import com.example.asus1.xiyousearch.Services.PersonalGradesService
import com.example.asus1.xiyousearch.URLUtil
import com.example.asus1.xiyousearch.User
import okhttp3.FormBody
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response

/**
 * Created by asus1 on 2018/2/5.
 */

class PersonalGradesFragment :Fragment,View.OnClickListener{

    private lateinit var mGrades:TextView
    private lateinit var mSemester:TextView
    private lateinit var mSearch:TextView
    private lateinit var mListView:ListView
    private val dataList :ArrayList<Grades> = arrayListOf()
    private lateinit var mAdapter:GradesApater

    private val xndList = arrayListOf<String>()
    private val xqdList = arrayListOf<String>()

    constructor()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater!!.inflate(R.layout.fragment_personalgrades,container,false)

        mGrades = view.findViewById(R.id.tv_grades)
        mGrades.setOnClickListener(this)
        mSemester = view.findViewById(R.id.tv_semester)
        mSemester.setOnClickListener(this)
        mSearch = view.findViewById(R.id.tv_search_grades)
        mSearch.setOnClickListener(this)
        mListView = view.findViewById(R.id.list_grades)

        mAdapter = GradesApater(context,R.layout.layout_grade_item,dataList)
        mListView.adapter = mAdapter

        init()

        return view
    }


    private fun init(){
        var getDataCall  = URLUtil.mainClient
                .create(PersonalGradesService::class.java).
                getData(URLUtil.user.name,
                        "����",
                        "N121605",
                        "http://222.24.62.120/xscjcx.aspx?xh="+URLUtil.user.name+"&xm=%C2%DE%C3%F4&gnmkdm=N121605"
                        )

        URLUtil.doRequest(getDataCall,callback)

    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.tv_grades ->{

                showWindow(mGrades,xndList)
            }

            R.id.tv_semester ->{
                showWindow(mSemester,xqdList)
            }

            R.id.tv_search_grades ->{
                searchGrade()
            }
        }
    }

    private fun searchGrade(){

        var body = FormBody.Builder()
        body.add("__EVENTTARGET","")
        body.add("__EVENTARGUMENT","")
        body.add("__VIEWSTATE",getString(R.string.viewstate))
        body.add("hidLanguage","")
        body.add("ddlXN",mGrades.text.toString())
        body.add("ddlXQ",mSemester.text.toString())
        body.add("ddl_kcxz","")
        body.add("btn_xq","ѧ�ڳɼ�")

        var gradesCall = URLUtil.mainClient.create(PersonalGradesService::class.java)
                .searchGredes(URLUtil.user.name,
                        "%C2%DE%C3%F4",
                        "N121605",
                        "http://222.24.62.120/xscjcx.aspx?xh="+URLUtil.user.name+"&xm=%C2%DE%C3%F4&gnmkdm=N121605",
                        body.build()
                )

        URLUtil.doRequest(gradesCall,GradeCallBack)
    }


    private fun showWindow(view: TextView,list: ArrayList<String>){
        val linearLayout = View.inflate(context,R.layout.view_popup,null)
        val viewGroup = linearLayout.findViewById<LinearLayout>(R.id.viewgroup)
        for (grades in list){
            var textView = TextView(context)
            textView.textSize = (12).toFloat()
            var layoutParams =
                    LinearLayout
                            .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.topMargin = 20
            textView.layoutParams = layoutParams
            textView.text = grades
            textView.setTextColor(resources.getColor(R.color.textColor))
            textView.setBackgroundResource(R.drawable.bg_leftdrawer_item)

            textView.setOnClickListener {
                view.text = grades
            }

            viewGroup.addView(textView)

        }

        var window = PopupWindow(linearLayout,
                view.measuredWidth,
                view.measuredHeight*list.size+50,
                true)
        window.isTouchable = true
        window.isOutsideTouchable = true
        window.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_window))
        window.showAsDropDown(view,0,20, Gravity.BOTTOM)

    }


    private var callback =object :URLUtil.CallBack<ResponseBody>{

        override fun getResponed(response: Response<ResponseBody>) {

            var element = Jsoup.parse(response.body().string())

            var xnd = element.select("#ddlXN")

            Log.d("gradesFrag",xnd.html())

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

        }
    }

    private var GradeCallBack = object :URLUtil.CallBack<ResponseBody>{
        override fun getResponed(response: Response<ResponseBody>) {
            var element = Jsoup.parse(response.body().string())

            var table = element.select("#Datagrid1")
            var list = table.select("tr")

            var i = 0
            dataList.clear()
            for (el in list){
                if(i>1){
                    var cl = arrayListOf<String>()
                    for (e in el.select("td")){
                        cl.add(e.text())
                        Log.d("GradeFragment",e.text())
                    }

                    var grades = Grades(cl[3],cl[4],cl[6],cl[7],cl[8])
                    dataList.add(grades)

                }else{
                    i++
                }
            }
            mAdapter.notifyDataSetChanged()
        }
    }
}