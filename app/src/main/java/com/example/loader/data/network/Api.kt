package com.example.loader.data.network

import com.example.loader.models.CourseList
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Headers

interface Api{
    @GET("netology.json")
    fun getData() : Call<ResponseBody>
}