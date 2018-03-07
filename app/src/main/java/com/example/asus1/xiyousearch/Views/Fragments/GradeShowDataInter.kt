package com.example.asus1.xiyousearch.Views.Fragments

import com.example.asus1.xiyousearch.Models.Grades

/**
 * Created by asus1 on 2018/3/7.
 */


interface GradeShowDataInter{
    fun showXData(xnd:ArrayList<String>,xqd:ArrayList<String>)
    fun showGradeData(grades:ArrayList<Grades>)
}