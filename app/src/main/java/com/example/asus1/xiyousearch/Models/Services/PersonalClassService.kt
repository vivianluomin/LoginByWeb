package com.example.asus1.xiyousearch.Models.Services

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by asus1 on 2018/2/5.
 */

interface PersonalClassService{

    @GET("/xskbcx.aspx")
    fun getClasses(@Query("xh") xh:String,
                   @Query("xm") xm:String,
                   @Query("gnmkdm") gn:String,
                   @Header("Referer") referer:String
                   ):Call<ResponseBody>


    @POST("/xskbcx.aspx")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun requestClasses(@Query("xh") xh:String,
                       @Query("xm") xm:String,
                       @Query("gnmkdm") gn:String,
                       @Header("Referer") referer:String,
                       @Body body: RequestBody
                       ):Call<ResponseBody>

}