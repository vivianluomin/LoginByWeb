package com.example.asus1.xiyousearch.Presenters

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by asus1 on 2018/2/3.
 */

class ReadCookie :Interceptor{

    override fun intercept(chain: Interceptor.Chain?): Response {
        if (chain == null) {
            return Response.Builder().code(1000).build()
        }

        var response = chain.proceed(chain.request())

        var list :List<String> =response.headers("Set-Cookie")
        var cookieString = ""
        for (str in list){
            println(str)
            cookieString+=str
        }

        com.example.asus1.xiyousearch.Presenters.URLUtil.instance.cookie = cookieString

        return response
    }
    
}