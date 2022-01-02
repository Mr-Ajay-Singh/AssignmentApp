package com.atmana.assignmentapp.RetrofitFiles

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DataService {

    @GET(value = "/api/users")
    suspend fun getData(@Query(value = "page") page : Int) : Users

    @GET(value = "api/users/{page}")
    suspend fun getDataFromPage(@Path(value = "page") page : Int) : Users

    companion object{
        private const val BASE_URL = "https://reqres.in/"
        operator fun invoke(): DataService = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(DataService::class.java)

    }
}