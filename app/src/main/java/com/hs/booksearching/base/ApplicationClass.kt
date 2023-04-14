package com.hs.booksearching.base

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {

    companion object {
        const val NAVER_API_URL = "https://openapi.naver.com"

        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        setRetrofit()
    }

    private fun setRetrofit() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(NAVER_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}