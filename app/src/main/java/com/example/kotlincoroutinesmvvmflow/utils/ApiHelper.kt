package com.example.kotlincoroutinesmvvmflow.utils

import com.example.kotlincoroutinesmvvmflow.network.Service

class ApiHelper(private val apiService: Service) {

    suspend fun getUsers() = apiService.getUsers()
}