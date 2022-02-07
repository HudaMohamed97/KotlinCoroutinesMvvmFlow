package com.example.kotlincoroutinesmvvmflow.reprositories

import com.example.kotlincoroutinesmvvmflow.network.Service

class MainRepository(private val apiService: Service) {
    suspend fun getVideoByUrl(url: String) = apiService.getVideo(url)
    suspend fun getUsers() = apiService.getUsers()
}