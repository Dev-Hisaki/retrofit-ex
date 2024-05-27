package com.hisaki.retrofitexample

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("endpoint")
    fun getData(): Call<MyData>
}