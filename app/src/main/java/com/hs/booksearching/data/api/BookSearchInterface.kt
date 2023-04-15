package com.hs.booksearching.data.api

import com.hs.booksearching.BuildConfig.NAVER_CLIENT_ID
import com.hs.booksearching.BuildConfig.NAVER_CLIENT_SECRET
import com.hs.booksearching.data.api.BookSearchApiClient.NAVER_API_URL
import com.hs.booksearching.data.model.BookSearch.BookSearchResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BookSearchInterface {

    @GET("v1/search/book.json")
    suspend fun getBookList(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String = "sim"     // sim: 정확도순으로 내림차순 정렬, date: 출간일순으로 내림차순 정렬
        ) : BookSearchResponse

    companion object {
        fun create(): BookSearchInterface {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val interceptor = Interceptor { chain ->
                with(chain) {
                    val newRequest = request().newBuilder()
                        .addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                        .addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                        .build()
                    proceed(newRequest)
                }
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(NAVER_API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchInterface::class.java)
        }
    }
}