package com.example.asus1.xiyousearch.Services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by asus1 on 2018/2/3.
 */

interface MoveService{

    @GET("/xs_main.aspx?")
    fun moveTo(@Query("xh")name:String):Call<ResponseBody>
}