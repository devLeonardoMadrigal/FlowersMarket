package com.example.flowersmarket.remote

import com.example.flowersmarket.model.FlowersResponseItem
import retrofit2.http.GET


//https://services.hanselandpetal.com/feeds/flowers.json

interface FlowerApi {
    @GET("feeds/flowers.json")

    suspend fun getFlowers() : List<FlowersResponseItem>
}