package com.example.asus1.xiyousearch.Activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.TextView
import com.example.asus1.xiyousearch.Fragments.*
import com.example.asus1.xiyousearch.R

class HomePageActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var  mHomePage:TextView
    private lateinit var  mPersonalClasses:TextView
    private lateinit var  mPersnalGrades:TextView
    private lateinit var  mSchoolPlan:TextView
    private lateinit var  mMakeUpExam :TextView
    private lateinit var  mChangePassword:TextView
    private lateinit var  mPersonalInfo:TextView

    private val mFragmentManager = supportFragmentManager
    private val mFragmentTransaction = mFragmentManager.beginTransaction()

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
        mSchoolPlan = findViewById(R.id.tv_school_plan)
        mSchoolPlan.setOnClickListener(this)
        mMakeUpExam = findViewById(R.id.tv_make_up_exam)
        mMakeUpExam.setOnClickListener(this)
        mChangePassword = findViewById(R.id.tv_change_password)
        mChangePassword.setOnClickListener(this)
        mPersonalInfo = findViewById(R.id.tv_personal_info)
        mPersonalInfo.setOnClickListener(this)



    }

    override fun onClick(p0: View?) {

        when(p0?.id){
            R.id.tv_homepage ->{
                if(R.id.tv_homepage!=mFlag){
                    mFragmentTransaction.replace(R.id.fg_content,HomePageFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_homepage
                }

            }
            R.id.tv_search_grades ->{
                if(R.id.tv_search_grades!=mFlag){
                    mFragmentTransaction.replace(R.id.fg_content,PersonalGradesFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_search_grades
                }

            }

            R.id.tv_personal_classes->{
                if (R.id.tv_personal_classes!=mFlag){
                    mFragmentTransaction.replace(R.id.fg_content,PersonalClassesFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_personal_classes
                }
            }

            R.id.tv_school_plan->{
                if(R.id.tv_school_plan!=mFlag){
                    mFragmentTransaction.replace(R.id.fg_content,SchoolPlanFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_school_plan
                }

            }

            R.id.tv_make_up_exam->{
                if (R.id.tv_make_up_exam!=mFlag){
                    mFragmentTransaction.replace(R.id.fg_content,MackUpExamFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_make_up_exam
                }

            }
            R.id.tv_change_password->{
                if(R.id.tv_change_password!=mFlag){
                    mFragmentTransaction.replace(R.id.fg_content,ChangePasswordFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_change_password
                }

            }
            R.id.tv_personal_info->{
                if(R.id.tv_personal_info!=mFlag){
                    mFragmentTransaction.replace(R.id.fg_content,PersonalInfoFragment())
                    mFragmentTransaction.commit()
                    mFlag = R.id.tv_personal_info
                }

            }


        }

    }
}
