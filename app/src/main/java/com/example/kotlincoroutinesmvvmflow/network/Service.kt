package com.example.kotlincoroutinesmvvmflow.network

import com.example.kotlincoroutinesmvvmflow.model.User
import retrofit2.http.GET

interface Service {
    @GET("users")
    suspend fun getUsers(): List<User>
}