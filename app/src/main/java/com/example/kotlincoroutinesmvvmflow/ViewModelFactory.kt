package com.example.kotlincoroutinesmvvmflow

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlincoroutinesmvvmflow.network.Service
import com.example.kotlincoroutinesmvvmflow.reprositories.MainRepository

class ViewModelFactory(
    private val applicationContext: Application,
    private val apiService: Service
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiService), applicationContext) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
