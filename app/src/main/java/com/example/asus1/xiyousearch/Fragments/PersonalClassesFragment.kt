package com.example.asus1.xiyousearch.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asus1.xiyousearch.R
import com.example.asus1.xiyousearch.Services.PersonalClassService
import com.example.asus1.xiyousearch.URLUtil
import com.example.asus1.xiyousearch.Views.CurriculumViews
import com.example.asus1.xiyousearch.Views.WeeksView
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * Created by asus1 on 2018/2/5.
 */

class PersonalClassesFragment :Fragment{

    private lateinit var mCurriculumViews: CurriculumViews
    private lateinit var mWeeksView:WeeksView


    constructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater!!.inflate(R.layout.fragment_personalclasses,container,false)

        mCurriculumViews = view.findViewById(R.id.curriculum)
        mWeeksView = view.findViewById(R.id.weeks)

        mCurriculumViews.addClass("java",mWeeksView.mClassSize/3+20,
                100,
                mWeeksView.mClassSize*2-150
                )


        return view
    }

    private fun getData(){
        var classesService = URLUtil.mainClient.
                create(PersonalClassService::class.java)

    }

    val callBack = object :URLUtil.CallBack<ResponseBody>{

        override fun getResponed(response: Response<ResponseBody>) {

        }
    }


}