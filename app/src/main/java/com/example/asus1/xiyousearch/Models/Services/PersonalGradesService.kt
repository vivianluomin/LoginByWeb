package com.example.asus1.xiyousearch.Models.Services

import okhttp3.FormBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by asus1 on 2018/2/11.
 */

interface PersonalGradesService{

    @GET("/xscjcx.aspx")
    fun getData(@Query("xh") name:String,
                @Query("xm") xm:String,
                @Query("gnmkdm") gn:String,
                @Header("Referer") header:String
                ):Call<ResponseBody>


    @POST("/xscjcx.aspx")
    fun searchGredes(@Query("xh") name:String,
                     @Query("xm") xm:String,
                     @Query("gnmkdm") gn:String,
                     @Header("Referer") header:String,
                     @Body body: FormBody

                    ):Call<ResponseBody>


}