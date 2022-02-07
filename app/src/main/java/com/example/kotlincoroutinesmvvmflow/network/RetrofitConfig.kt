package com.example.kotlincoroutinesmvvmflow.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitConfig {
    // Base url of the api
    private const val BASE_URL = "https://61fb0e8387801d0017a2c3dd.mockapi.io"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()}

    val API_SERVICE: Service = getRetrofit().create(Service::class.java)
}