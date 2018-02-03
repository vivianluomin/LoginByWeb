package com.example.asus1.xiyousearch.Activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.asus1.xiyousearch.R
import com.example.asus1.xiyousearch.Services.LoginService
import com.example.asus1.xiyousearch.Services.MoveService
import com.example.asus1.xiyousearch.Services.getCookieService
import com.example.asus1.xiyousearch.URLUtil
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mUserName:EditText
    private lateinit  var mUserPassword :EditText
    private lateinit var mCheckCode : EditText
    private lateinit var mLoginText : TextView
    private lateinit var mChechCodeImage : ImageView

    private var  mClickCount = 0

    private var TAG :String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init(){
        mUserName = findViewById(R.id.et_username)
        mUserPassword = findViewById(R.id.et_userpassword)
        mCheckCode = findViewById(R.id.et_checkcode)
        mChechCodeImage = findViewById(R.id.iv_checkcode)
        mLoginText = findViewById(R.id.tv_login)


        Glide.with(this)
                .load(URLUtil.CHECK_CODE)
                .error(R.drawable.bg_erro)
                .placeholder(R.drawable.bg_erro)
                .into(mChechCodeImage)
        mClickCount ++


        setListener()

    }

    private fun  setListener(){

        mChechCodeImage.setOnClickListener(View.OnClickListener {
            var url :String  = URLUtil.CHECK_CODE
            for (i in 1..mClickCount){
                url+="?"
            }

            Log.d(TAG,url)

            Glide.with(this)
                    .load(url)
                    .error(R.drawable.bg_erro)
                    .placeholder(R.drawable.bg_erro)
                    .into(mChechCodeImage)
            mClickCount ++


        })

        mLoginText.setOnClickListener(View.OnClickListener {

            val cookieService = URLUtil.client.create(getCookieService::class.java).getCookie()
            URLUtil.doRequest(cookieService,callback)


        })

    }

    val callback :URLUtil.CallBack<ResponseBody> = object : URLUtil.CallBack<ResponseBody> {


        override fun getResponed(response: Response<ResponseBody>) {
            Log.d(TAG,""+response.code())
            if(response.code() == 200){

                val name  = mUserName.text.toString()
                val password = mUserPassword.text.toString()
                val checkcode = mCheckCode.text.toString()



                val loginService :Call<ResponseBody> = URLUtil.client
                        .create(LoginService::class.java)
                        .doLogin(name,password,checkcode,URLUtil.STUDENT)

                URLUtil.doRequest(loginService,moveCallback)
            }

        }
    }

    val moveCallback =  object :URLUtil.CallBack<ResponseBody>{
        override fun getResponed(response: Response<ResponseBody>) {
            Log.d(TAG,response.body().string())
            Log.d(TAG,""+response.code()+" move")
        }
    }
}
