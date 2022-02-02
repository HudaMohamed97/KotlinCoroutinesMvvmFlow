package com.example.kotlincoroutinesmvvmflow.reprositories

import com.example.kotlincoroutinesmvvmflow.utils.ApiHelper

class MainRepository(private val apiService: ApiHelper) {
    suspend fun getUsers() {
        apiService.getUsers()
    }
}