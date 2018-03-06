package com.example.asus1.xiyousearch.Models.Services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by asus1 on 2018/2/12.
 */

interface PersonalInfoService{

    @GET("/xsgrxx.aspx")
    fun getInfo(
            @Query("xh") name:String,
            @Query("xm") xm:String,
            @Query("gnmkdm") gn:String,
            @Header("Referer") header:String

    ):Call<ResponseBody>
}