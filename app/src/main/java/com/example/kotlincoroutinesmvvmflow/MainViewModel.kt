package com.example.kotlincoroutinesmvvmflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kotlincoroutinesmvvmflow.reprositories.MainRepository
import com.example.kotlincoroutinesmvvmflow.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: MainRepository) : ViewModel() {

     fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
