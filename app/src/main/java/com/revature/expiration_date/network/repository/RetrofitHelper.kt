package com.revature.expiration_date.network.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private val retrofit: Retrofit

    init {
        val builder = Retrofit.Builder()
            .baseUrl("https://private-d6f8a-project2expirationdate.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES).build()

        retrofit = builder.client(okHttpClient).build()
    }

    fun getAPIService(): APIService {
        return retrofit.create(APIService::class.java)
    }
}