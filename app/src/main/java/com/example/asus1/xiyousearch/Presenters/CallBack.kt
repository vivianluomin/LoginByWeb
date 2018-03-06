package com.example.asus1.xiyousearch.Presenters

import retrofit2.Response

/**
 * Created by asus1 on 2018/3/6.
 */
interface  CallBack<T>{
    fun getResponed(response: Response<T>)
}