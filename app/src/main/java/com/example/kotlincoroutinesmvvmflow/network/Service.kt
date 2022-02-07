package com.example.kotlincoroutinesmvvmflow.network

import com.example.kotlincoroutinesmvvmflow.model.FakeResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming
import retrofit2.http.Url

interface Service {
    @GET("fakeResponse/fake")
    suspend fun getUsers(): List<FakeResponse>

    /* **
     * This method uses Retrofit's @Streaming annotation to indicate
     * that the method is going to access a large stream of data
     * (e.g., the mpeg video data on the server).  The client can
     * access this stream of data by obtaining an InputStream from the
     * Response as shown below:
     *
     * VideoServiceProxy client = ... // use retrofit to create the client
     * Response response = client.getData(someVideoId);
     * InputStream videoDataStream = response.getBody().in();
     *
     * @param id
     * @return Response which contains the actual Video data.
     **/
    @Streaming
    @GET
    suspend fun getVideo(@Url fileUrl: String): Response<ResponseBody>
}