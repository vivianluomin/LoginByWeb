package com.example.asus1.xiyousearch.Views.Activities

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
import com.example.asus1.xiyousearch.Models.Services.LoginService
import com.example.asus1.xiyousearch.Models.Services.getCookieService
import com.example.asus1.xiyousearch.Presenters.URLUtil
import com.example.asus1.xiyousearch.Models.User
import com.example.asus1.xiyousearch.Presenters.CallBack
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
        mUserName.setText("05158091")
        mUserPassword = findViewById(R.id.et_userpassword)
        mUserPassword.setText("abc@+1234...")
        mCheckCode = findViewById(R.id.et_checkcode)
        mChechCodeImage = findViewById(R.id.iv_checkcode)
        mLoginText = findViewById(R.id.tv_login)

        getCookie()
        setListener()

    }


    private fun getCookie(){
        var CookieService = URLUtil.instance.client.create(getCookieService::class.java).getCookie(URLUtil.instance.CHECK_CODE)
        URLUtil.instance.doRequest(CookieService,cookieCallBack)

    }

    private fun  setListener(){

        mChechCodeImage.setOnClickListener(View.OnClickListener {


            changeCheckCode()
        })

        mLoginText.setOnClickListener(View.OnClickListener {

            val name  = mUserName.text.toString()
            val password = mUserPassword.text.toString()
            val checkcode = mCheckCode.text.toString()

            val loginService :Call<ResponseBody> = URLUtil.instance.mainClient
                    .create(LoginService::class.java)
                    .doLogin("dDwxNTMxMDk5Mzc0Ozs+lYSKnsl/mKGQ7CKkWFJpv0btUa8="
                            ,name
                            ,""
                            ,password
                            ,checkcode
                            , URLUtil.instance.STUDENT
                            ,""
                            ,""
                            ,""
                            )

            URLUtil.instance.doRequest(loginService,callback)


        })

    }

    private fun changeCheckCode(){
        var url :String  = URLUtil.instance.CHECK_CODE
        for (i in 1..mClickCount){
            url+="?"
        }

        Log.d(TAG,url)

        var CookieService = URLUtil.instance.mainClient.create(getCookieService::class.java).getCookie(url.trim())
        URLUtil.instance.doRequest(CookieService,cookieCallBack)
    }


   private fun saveImage(response: Response<ResponseBody>){
        Glide.with(this)
                .load(response.body().bytes())
                .error(R.drawable.bg_erro)
                .placeholder(R.drawable.bg_erro)
                .into(mChechCodeImage)
       mClickCount ++
    }

    val cookieCallBack = object : CallBack<ResponseBody>{
        override fun getResponed(response: Response<ResponseBody>) {
            Log.d(TAG,"getcookie"+response.code())
            if(response.code() == 200){
                saveImage(response)

            }

        }
    }

    val callback : CallBack<ResponseBody> = object : CallBack<ResponseBody> {


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


                URLUtil.instance.user = User(mUserName.text.toString(), mUserPassword.text.toString())
                startActivity(Intent(this@MainActivity, HomePageActivity::class.java))

                finish()
            }
        }
    }


}
