package com.hansoft.tryglidesixth

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import retrofit2.Call

import retrofit2.Callback

import retrofit2.Response
import kotlin.math.log


class OkhttpActivity : AppCompatActivity() {

    var tvName: TextView? = null
    private var service: GitHubService? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)
        tvName = findViewById(R.id.tv_name)
        ButterKnife.bind(this)
        initHttp()
        Log.d("aaa", "onCreate: aaa")
        requestHttp()
        Log.d("aaa", "onCreate: bbb")
        // requestRxHttp();
    }

    private fun initHttp() {
        service = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpProvider.okHttpInstance)
                .build()
                .create(GitHubService::class.java)
    }

    private fun requestRxHttp() {
        /*
        service!!.getRxUser("qingmei2")!!
                 .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(User::login)
                .subscribe(tvName::setText, System.out::print)

         */
    }

    private fun requestHttp() {
        service!!.getUser("qingmei2")!!.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>?, response: Response<User?>) {
                val user: User? = response.body()
                if (user == null) {
                    Log.d("aaa", "onResponse: user is null")
                    tvName!!.setText("null")
                } else {
                    tvName!!.setText(user.login)
                    Log.d("aaa", "onResponse: login = " + user.login)
                    Log.d("aaa", "onResponse: name = " + user.name)
                }
            }

            override fun onFailure(call: Call<User?>?, t: Throwable) {
                tvName!!.setText(t.message)
                Log.d("aaa", "onFailure: error is " + t.localizedMessage)
            }
        })
    }
}