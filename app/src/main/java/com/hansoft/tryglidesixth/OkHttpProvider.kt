package com.hansoft.tryglidesixth

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpProvider {
    private var instance: OkHttpClient? = null
    val okHttpInstance: OkHttpClient?
        get() {
            if (instance == null) {
                val interceptor = HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                instance = OkHttpClient()
                        .newBuilder()
                        .addInterceptor(interceptor)
                        .build()
            }
            return instance
        }
}