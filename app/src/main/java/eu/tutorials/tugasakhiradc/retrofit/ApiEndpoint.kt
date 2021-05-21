package eu.tutorials.tugasakhiradc.retrofit

import eu.tutorials.tugasakhiradc.MainModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("data.php")
    fun getData(): Call<MainModel>

}