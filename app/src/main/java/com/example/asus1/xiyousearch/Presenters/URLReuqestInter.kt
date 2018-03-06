package com.example.asus1.xiyousearch.Presenters

import retrofit2.Call

/**
 * Created by asus1 on 2018/3/6.
 */

interface URLReuqestInter{

    fun<T> doRequest(call: Call<T>, callBack: CallBack<T>)
}