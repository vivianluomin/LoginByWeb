package com.example.asus1.xiyousearch.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asus1.xiyousearch.R

/**
 * Created by asus1 on 2018/2/5.
 */

class PersonalClassesFragment :Fragment{
    constructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_personalclasses,container,false)
    }


}