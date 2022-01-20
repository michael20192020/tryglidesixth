package com.hansoft.tryglidesixth

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */
interface GitHubService {
    @GET("users/{user}")
    fun getUser(@Path("user") user: String?): Call<User?>?

    @GET("users/{user}")
    fun getRxUser(@Path("user") user: String?): Observable<User?>?
}