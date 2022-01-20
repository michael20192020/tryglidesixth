package com.hansoft.tryglidesixth

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */
class GitHubServiceManager {
    private var service: GitHubService? = null
    private fun init() {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
        service = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)
            .build()
            .create<GitHubService>(GitHubService::class.java)
    }

    fun getUser(user: String?): Observable<User?>? {
        return service!!.getRxUser(user)
    }

    init {
        init()
    }
}