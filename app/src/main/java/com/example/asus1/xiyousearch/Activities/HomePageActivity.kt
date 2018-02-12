package com.example.asus1.xiyousearch.Activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.asus1.xiyousearch.Fragments.*
import com.example.asus1.xiyousearch.R

class HomePageActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var  mHomePage:TextView
    private lateinit var  mPersonalClasses:TextView
    private lateinit var  mPersnalGrades:TextView

    private lateinit var  mPersonalInfo:TextView

    private val TAG = "HomePageActivity"

    private val mFragmentManager = supportFragmentManager
    private var mFragmentTransaction = mFragmentManager.beginTransaction()

    private var mFlag = R.id.tv_homepage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        init()
    }

    private fun init(){
        mHomePage = findViewById(R.id.tv_homepage)
        mHomePage.setOnClickListener(this)
        mPersonalClasses = findViewById(R.id.tv_personal_classes)
        mPersonalClasses.setOnClickListener(this)
        mPersnalGrades = findViewById(R.id.tv_search_grades)
        mPersnalGrades.setOnClickListener(this)

        mPersonalInfo = findViewById(R.id.tv_personal_info)
        mPersonalInfo.setOnClickListener(this)


        mFragmentTransaction = mFragmentManager.beginTransaction()
        mFragmentTransaction.replace(R.id.fg_content,HomePageFragment())
        mFragmentTransaction.commit()
    }

    override fun onClick(p0: View?) {

        when(p0?.id){
            R.id.tv_homepage ->{
                if(R.id.tv_homepage!=mFlag){
                    mFragmentTransaction = mFragmentManager.beginTransaction()
                    mFragmentTransaction.replace(R.id.fg_content,HomePageFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_homepage
                }

            }
            R.id.tv_search_grades ->{
                if(R.id.tv_search_grades!=mFlag){
                    mFragmentTransaction = mFragmentManager.beginTransaction()
                    mFragmentTransaction.replace(R.id.fg_content,PersonalGradesFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_search_grades
                }

            }

            R.id.tv_personal_classes->{
                if (R.id.tv_personal_classes!=mFlag){
                    mFragmentTransaction = mFragmentManager.beginTransaction()
                    mFragmentTransaction.replace(R.id.fg_content,PersonalClassesFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_personal_classes
                }
            }



            R.id.tv_personal_info->{
                if(R.id.tv_personal_info!=mFlag){
                    mFragmentTransaction = mFragmentManager.beginTransaction()
                    mFragmentTransaction.replace(R.id.fg_content,PersonalInfoFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_personal_info
                }

            }


        }

    }
}
