package com.example.asus1.xiyousearch.Interceptors

import android.webkit.URLUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by asus1 on 2018/2/3.
 */

class UseCookie:Interceptor{
    override fun intercept(chain: Interceptor.Chain?): Response {
        if (chain == null) {
            return Response.Builder().code(1000).build()
        }

        var response = chain.request()

        var builder = response.newBuilder().addHeader("Cookie",com.example.asus1.xiyousearch.URLUtil.cookie)
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .addHeader("Host","222.24.62.120")
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")




        return chain.proceed(builder.build())

    }
}