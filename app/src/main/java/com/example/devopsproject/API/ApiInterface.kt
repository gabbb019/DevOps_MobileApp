package com.example.devopsproject.API

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET(".")
    fun getData(): Call<MyData>
}