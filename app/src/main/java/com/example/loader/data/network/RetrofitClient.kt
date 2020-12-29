package com.example.loader.data.network

import com.example.loader.util.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient(client: OkHttpClient) {

    var gson = GsonBuilder()
            .setLenient()
            .create()

    val api: Api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api::class.java)

}