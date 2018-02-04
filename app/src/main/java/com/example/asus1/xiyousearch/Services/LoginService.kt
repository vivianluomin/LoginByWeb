package com.example.asus1.xiyousearch.Services

import android.webkit.URLUtil
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by asus1 on 2018/2/3.
 */

interface LoginService{

    @POST("/default2.aspx")
    @FormUrlEncoded
    fun doLogin(@Field("__VIEWSTATE") viewstate:String,
                @Field("txtUserName")name :String,
                @Field("Textbox1") textbox1:String,
                @Field("TextBox2") password:String,
                @Field("txtSecretCode") checkCode:String,
                @Field("RadioButtonList1") student:String,
                @Field("Button1") botton1:String,
                @Field("lbLanguage") language:String,
                @Field("hidPdrs") pdra:String

    ):Call<ResponseBody>
}