package com.example.flowersmarket.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val api : FlowerApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://services.hanselandpetal.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlowerApi::class.java)
    }
}