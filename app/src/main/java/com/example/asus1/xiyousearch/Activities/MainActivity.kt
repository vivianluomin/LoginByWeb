package com.example.asus1.xiyousearch.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.asus1.xiyousearch.R
import com.example.asus1.xiyousearch.Services.LoginService
import com.example.asus1.xiyousearch.Services.getCookieService
import com.example.asus1.xiyousearch.URLUtil
import okhttp3.ResponseBody
import org.jsoup.Jsoup
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

        getCookie()
        setListener()

    }


    private fun getCookie(){
        var CookieService = URLUtil.client.create(getCookieService::class.java).getCookie(URLUtil.CHECK_CODE)
        URLUtil.doRequest(CookieService,cookieCallBack)

    }

    private fun  setListener(){

        mChechCodeImage.setOnClickListener(View.OnClickListener {


            changeCheckCode()
        })

        mLoginText.setOnClickListener(View.OnClickListener {

            val name  = mUserName.text.toString()
            val password = mUserPassword.text.toString()
            val checkcode = mCheckCode.text.toString()

            val loginService :Call<ResponseBody> = URLUtil.mainClient
                    .create(LoginService::class.java)
                    .doLogin("dDwxNTMxMDk5Mzc0Ozs+lYSKnsl/mKGQ7CKkWFJpv0btUa8="
                            ,name
                            ,""
                            ,password
                            ,checkcode
                            ,URLUtil.STUDENT
                            ,""
                            ,""
                            ,""
                            )

            URLUtil.doRequest(loginService,callback)


        })

    }

    private fun changeCheckCode(){
        var url :String  = URLUtil.CHECK_CODE
        for (i in 1..mClickCount){
            url+="?"
        }

        Log.d(TAG,url)

        var CookieService = URLUtil.mainClient.create(getCookieService::class.java).getCookie(url.trim())
        URLUtil.doRequest(CookieService,cookieCallBack)
    }


   private fun saveImage(response: Response<ResponseBody>){
        Glide.with(this)
                .load(response.body().bytes())
                .error(R.drawable.bg_erro)
                .placeholder(R.drawable.bg_erro)
                .into(mChechCodeImage)
       mClickCount ++
    }

    val cookieCallBack = object :URLUtil.CallBack<ResponseBody>{
        override fun getResponed(response: Response<ResponseBody>) {
            Log.d(TAG,"getcookie"+response.code())
            if(response.code() == 200){
                saveImage(response)

            }

        }
    }

    val callback :URLUtil.CallBack<ResponseBody> = object : URLUtil.CallBack<ResponseBody> {


        override fun getResponed(response: Response<ResponseBody>) {

            Log.d(TAG,"Login code："+response.code())
            val elements = Jsoup.parse(response.body().string())
                    .select("script")
            Log.d(TAG,"elements :"+elements.size)
            if(elements.size==2){
                val element = elements[1]
                val alrt = element.html().toString().split(";")[0]
                Toast.makeText(this@MainActivity,alrt,Toast.LENGTH_SHORT).show()
                changeCheckCode()
            }else{

                startActivity(Intent(this@MainActivity,HomePageActivity::class.java))

            }
        }
    }


}
