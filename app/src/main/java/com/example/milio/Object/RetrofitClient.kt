package com.example.milio.Object

import com.example.milio.Interface.API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
     const val BASE_URL_CHAT="https://dev-service.rightsledger.global/"
     const val BASE_URL_LIFESTYLE="https://dev-service.rightsledger.global/service-core-milio/"
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("token_key", "61748b04-eb3e-401e-9d78-297771e788ed")
                .addHeader("hash_key", "MmFiZjNhNzM1ZGQ5YTIwMWJiZTQ5Y2QyMWUxMzEyYjY3MmU3ZDhiZA==")
                .addHeader("started", "1573098079373")
                .method(original.method(), original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
    val instance: API by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_CHAT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(API::class.java)
    }

    val instanceLifeStyle: API by lazy{

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_LIFESTYLE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(API::class.java)
    }
}