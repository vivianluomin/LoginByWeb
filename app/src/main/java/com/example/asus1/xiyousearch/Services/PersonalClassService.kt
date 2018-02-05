package com.example.asus1.xiyousearch.Services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by asus1 on 2018/2/5.
 */

interface PersonalClassService{

    @GET("/xskbcx.aspx?")
    fun getClasses(@Query("xh") xh:String,
                   @Query("xm") xm:String,
                   @Query("gnmkdm") gn:String
                   ):Call<ResponseBody>

}