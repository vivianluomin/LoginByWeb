package com.example.asus1.xiyousearch.Models.Services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by asus1 on 2018/2/3.
 */
interface getCookieService {

    @GET()
    fun getCookie(@Url s:String):Call<ResponseBody>
}