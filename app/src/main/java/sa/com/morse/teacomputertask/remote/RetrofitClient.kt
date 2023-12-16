package com.morse.differencesbetweennetworklibraries.network.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import sa.com.morse.teacomputertask.BuildConfig
import sa.com.morse.teacomputertask.remote.MoviesApi
import java.util.concurrent.TimeUnit

object RetrofitAgent {

    private val interceptors = arrayListOf(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }, Interceptor { chain ->
        val request = chain.request().newBuilder()
        chain.proceed(request.build())
    })

    private val gate = Retrofit.Builder()
        .client(OkHttpClient.Builder().apply {
            interceptors.forEach {
                addInterceptor(it)
            }
            writeTimeout(1, TimeUnit.MINUTES)
            readTimeout(1, TimeUnit.MINUTES)
            connectTimeout(1, TimeUnit.MINUTES)
        }.build())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.api)
        .build()

    fun  createGateway ()= gate.create(MoviesApi::class.java)

}

