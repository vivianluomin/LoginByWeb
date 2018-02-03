package com.example.asus1.xiyousearch.Services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by asus1 on 2018/2/3.
 */
interface getCookieService {

    @GET("/")
    fun getCookie():Call<ResponseBody>
}