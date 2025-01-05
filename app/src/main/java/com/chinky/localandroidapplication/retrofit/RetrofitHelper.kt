package com.chinky.localandroidapplication.retrofit

import com.chinky.localandroidapplication.repository.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private fun getRetrofit(): Retrofit {
        val baseUrl = "http://192.168.1.3:8080/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): ApiService = getRetrofit().create(ApiService::class.java)
}