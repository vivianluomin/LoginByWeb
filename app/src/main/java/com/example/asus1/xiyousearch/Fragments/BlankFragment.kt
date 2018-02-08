package com.example.asus1.xiyousearch.Fragments

import android.os.Bundle
import android.support.v4.R
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by asus1 on 2018/2/8.
 */

class BlankFragment :Fragment{

    constructor()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(com.example.asus1.xiyousearch.R.layout.fragment_blank,container,false)
    }
}