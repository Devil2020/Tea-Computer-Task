package sa.com.morse.teacomputertask.remote

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sa.com.morse.teacomputertask.BuildConfig
import java.util.concurrent.TimeUnit

object RetrofitAgent {

    private val interceptors = arrayListOf(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }, Interceptor { chain ->
        val request = chain.request().newBuilder()
        request.addHeader("accept", "application/json")
        request.addHeader("Authorization", BuildConfig.accessToken)

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
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BuildConfig.api)
        .build()

    fun createGateway() = gate.create(MoviesApi::class.java)

}

