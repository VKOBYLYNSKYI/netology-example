package com.example.loader.models.bodies

import com.example.loader.models.CourseList
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response

data class ListWrapper(
        @SerializedName("message") val message: String,
        ///@SerializedName("result") val result: Call<Response>
)