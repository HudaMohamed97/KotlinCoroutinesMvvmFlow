package com.example.kotlincoroutinesmvvmflow

import android.app.Application
import android.content.ContentValues.TAG
import android.os.Environment
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.kotlincoroutinesmvvmflow.reprositories.MainRepository
import com.example.kotlincoroutinesmvvmflow.utils.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import java.io.*


class MainViewModel(private val mainRepository: MainRepository, application: Application) :
    AndroidViewModel(
        application
    ) {

    private val context = getApplication<Application>().applicationContext

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getVideoByUrl(url: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = mainRepository.getVideoByUrl(url)
            emit(Resource.success(data = data))
            val fileName = url.substring(url.lastIndexOf("/") + 1)
            data.body()?.let { writeResponseBodyToDisk(it) }

        } catch (exception: Exception) {
            Log.i("error", "exception   " + exception.message)
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
        return try {
            // todo change the file location/name according to your needs
            val path =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            val futureStudioIconFile =
                File(path, "Icon.pdf")
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)
                while (true) {
                    val read = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    Log.d(TAG, "file download: $fileSizeDownloaded of $fileSize")
                }
                outputStream.flush()
                true
            } catch (e: IOException) {
                false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            false
        }
    }
}
