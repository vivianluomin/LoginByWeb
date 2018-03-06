package com.example.asus1.xiyousearch.Views.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import com.example.asus1.xiyousearch.R
import com.example.asus1.xiyousearch.Models.Services.PersonalClassService
import com.example.asus1.xiyousearch.Presenters.CallBack
import com.example.asus1.xiyousearch.Presenters.URLUtil
import com.example.asus1.xiyousearch.Views.MViews.CurriculumViews
import com.example.asus1.xiyousearch.Views.MViews.WeeksView
import okhttp3.FormBody
import okhttp3.ResponseBody
import org.jsoup.Jsoup
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

    private var isFirst = true

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
        var classesService = URLUtil.instance.mainClient.
                create(PersonalClassService::class.java)
                .getClasses(URLUtil.instance.user.name,
                        "%C2%DE%C3%F4",
                        "N121603",
                        "http://222.24.62.120/xskbcx.aspx?xh="+ URLUtil.instance.user.name+"&xm=%25C2%25DE%25C3%25F4&gnmkdm=N121603")
        URLUtil.instance.doRequest(classesService,callBack)

    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_grades->{
                showWindow(mGrades,xndList,"xnd")

            }

            R.id.tv_semester->{
                showWindow(mSemester,xqdList,"xqd")

            }
        }
    }


    private fun showWindow(view: TextView,list: ArrayList<String>,type:String){
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

                var builder = FormBody.Builder()
                builder.add("__EVENTTARGET",type)
                builder.add("__EVENTARGUMENT","")
                builder.add("__VIEWSTATE","dDwtMTgxNTE1MDA0NDt0PDtsPGk8MT47PjtsPHQ8O2w8aTwxPjtpPDI+O2k8ND47aTw3PjtpPDk+O2k8MTE+O2k8MTM+O2k8MTU+O2k8MjQ+O2k8MjY+O2k8Mjg+O2k8MzA+O2k8MzI+O2k8MzQ+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPFxlOz4+Oz47Oz47dDx0PHA8cDxsPERhdGFUZXh0RmllbGQ7RGF0YVZhbHVlRmllbGQ7PjtsPHhuO3huOz4+Oz47dDxpPDI+O0A8MjAxNy0yMDE4OzIwMTUtMjAxNjs+O0A8MjAxNy0yMDE4OzIwMTUtMjAxNjs+PjtsPGk8MD47Pj47Oz47dDx0PDs7bDxpPDE+Oz4+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85a2m5Y+377yaMDUxNTgwOTE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOWnk+WQje+8mue9l+aVjzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85a2m6Zmi77ya6K6h566X5py65a2m6ZmiOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzkuJPkuJrvvJrova/ku7blt6XnqIs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOihjOaUv+ePre+8mui9r+S7tjE2MDE7Pj47Pjs7Pjt0PDtsPGk8MT47PjtsPHQ8QDA8Ozs7Ozs7Ozs7Oz47Oz47Pj47dDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs+PjtsPGk8MT47PjtsPHQ8QDA8Ozs7Ozs7Ozs7Oz47Oz47Pj47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE+O2k8MD47aTwwPjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8cDxwPGw8UGFnZUNvdW50O18hSXRlbUNvdW50O18hRGF0YVNvdXJjZUl0ZW1Db3VudDtEYXRhS2V5czs+O2w8aTwxPjtpPDE+O2k8MT47bDw+Oz4+Oz47Ozs7Ozs7Ozs7PjtsPGk8MD47PjtsPHQ8O2w8aTwxPjs+O2w8dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM+O2k8ND47aTw1PjtpPDY+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOi9r+S7tuW3peeoi+ivvueoi+iuvuiuoeKFoTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85byg5b635oWnOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwyLjA7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDAxLTE4Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47Pj47Pj47Pj47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE+O2k8MD47aTwwPjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8cDxwPGw8UGFnZUNvdW50O18hSXRlbUNvdW50O18hRGF0YVNvdXJjZUl0ZW1Db3VudDtEYXRhS2V5czs+O2w8aTwxPjtpPDI+O2k8Mj47bDw+Oz4+Oz47Ozs7Ozs7Ozs7PjtsPGk8MD47PjtsPHQ8O2w8aTwxPjtpPDI+Oz47bDx0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0Pjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwyMDE3LTIwMTg7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOi9r+S7tuW3peeoi+ivvueoi+iuvuiuoeKFoTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85byg5b635oWnOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwyLjA7Pj47Pjs7Pjs+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0Pjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwyMDE3LTIwMTg7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOaVsOWtl+eUtei3r+WunumqjDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w86YOt5Y2OOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwxLjA7Pj47Pjs7Pjs+Pjs+Pjs+Pjs+Pjs+Pjs+Os+sBDH2ePfXhyoTDrqRFZ6+svc=")
                builder.add("xnd",mGrades.text.toString())
                builder.add("xqd",mSemester.text.toString())



                var requestCall = URLUtil.instance.
                        mainClient.
                        create(PersonalClassService::class.java).
                        requestClasses(
                                URLUtil.instance.user.name,
                                "%C2%DE%C3%F4",
                                "N121603",
                                "http://222.24.62.120/xskbcx.aspx?xh="+ URLUtil.instance.user.name+"&xm=%25C2%25DE%25C3%25F4&gnmkdm=N121603",
                                builder.build()
                        )

                URLUtil.instance.doRequest(requestCall,requestCallBack)

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
        window.showAsDropDown(view,0,20,Gravity.BOTTOM)

    }



    private fun showData(response: Response<ResponseBody>){

        mCurriculumViews.clearViews()
        xndList.clear()
        xqdList.clear()
        classList.clear()

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

    val callBack = object : CallBack<ResponseBody>{

        override fun getResponed(response: Response<ResponseBody>) {
            showData(response)

        }


    }

    val requestCallBack = object : CallBack<ResponseBody>{
        override fun getResponed(response: Response<ResponseBody>) {
            showData(response)
        }
    }


}