package com.example.asus1.xiyousearch

import com.example.asus1.xiyousearch.Interceptors.ReadCookie
import com.example.asus1.xiyousearch.Interceptors.UseCookie
import com.example.asus1.xiyousearch.Module.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by asus1 on 2018/2/3.
 */

object URLUtil{

    val CHECK_CODE :String = "http://222.24.62.120/CheckCode.aspx"
    val LOGIN_URL :String = "http://222.24.62.120"
    val LOGIN_MOVE:String = "/xs_main.aspx?xh="

    val STUDENT  = "ѧ��"

    var cookie :String = ""

    lateinit var user: User

    interface  CallBack<T>{
        fun getResponed(response: Response<T>)
    }

    fun<T> doRequest(call: Call<T>,callBack: CallBack<T>){

        call.enqueue(object :Callback<T>{

            override fun onFailure(call: Call<T>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {

               if (response!=null&&response.isSuccessful){
                   callBack.getResponed(response)
               }
            }

        })



    }

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ReadCookie())
            .build()

    private val cookieClient = OkHttpClient.Builder()
            .addInterceptor(UseCookie())
            .build()

     val client :Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(LOGIN_URL).build()

    val mainClient = Retrofit.Builder()
            .client(cookieClient)
            .baseUrl(LOGIN_URL)
            .build()






}