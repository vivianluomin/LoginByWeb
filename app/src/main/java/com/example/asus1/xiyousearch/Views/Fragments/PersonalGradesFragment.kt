package com.example.asus1.xiyousearch.Views.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.TextView
import com.example.asus1.xiyousearch.Views.Activities.GradesApater
import com.example.asus1.xiyousearch.Models.Grades
import com.example.asus1.xiyousearch.R
import com.example.asus1.xiyousearch.Models.Services.PersonalGradesService
import com.example.asus1.xiyousearch.Presenters.CallBack
import com.example.asus1.xiyousearch.Presenters.GradeCall
import com.example.asus1.xiyousearch.Presenters.GradeXCall
import com.example.asus1.xiyousearch.Presenters.URLUtil
import okhttp3.FormBody
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response

/**
 * Created by asus1 on 2018/2/5.
 */

class PersonalGradesFragment :Fragment,View.OnClickListener,GradeShowDataInter{

    private lateinit var mGrades:TextView
    private lateinit var mSemester:TextView
    private lateinit var mSearch:TextView
    private lateinit var mListView:ListView
    private val dataList :ArrayList<Grades> = arrayListOf()
    private lateinit var mAdapter:GradesApater

    private val xndList = arrayListOf<String>()
    private val xqdList = arrayListOf<String>()

    private var callback = GradeXCall(this)
    private var GradeCallBack = GradeCall(this)

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
        var getDataCall  = URLUtil.instance.mainClient
                .create(PersonalGradesService::class.java).
                getData(URLUtil.instance.user.name,
                        "����",
                        "N121605",
                        "http://222.24.62.120/xscjcx.aspx?xh="+ URLUtil.instance.user.name+"&xm=%C2%DE%C3%F4&gnmkdm=N121605"
                        )

        URLUtil.instance.doRequest(getDataCall,callback)

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
                if(mGrades.text!=""&&mSemester.text!="")
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

        var gradesCall = URLUtil.instance.mainClient.create(PersonalGradesService::class.java)
                .searchGredes(URLUtil.instance.user.name,
                        "%C2%DE%C3%F4",
                        "N121605",
                        "http://222.24.62.120/xscjcx.aspx?xh="+ URLUtil.instance.user.name+"&xm=%C2%DE%C3%F4&gnmkdm=N121605",
                        body.build()
                )

        URLUtil.instance.doRequest(gradesCall,GradeCallBack)
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


    override fun showXData(xnd: ArrayList<String>, xqd: ArrayList<String>) {
        xndList.clear()
        xqdList.clear()
        xndList.addAll(xnd)
        xqdList.addAll(xqd)

    }

    override fun showGradeData(grades: ArrayList<Grades>) {
        dataList.clear()
        dataList.addAll(grades)
        mAdapter.notifyDataSetChanged()
    }



}