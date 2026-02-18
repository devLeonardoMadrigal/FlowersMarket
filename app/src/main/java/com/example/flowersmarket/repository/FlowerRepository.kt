package com.example.flowersmarket.repository

import com.example.flowersmarket.model.FlowersResponseItem
import com.example.flowersmarket.remote.RetrofitClient

class FlowerRepository {

    suspend fun getFlowers() : Result<List<FlowersResponseItem>> = try {
        val flowers = RetrofitClient.api.getFlowers()
        Result.success(flowers)

    } catch (e: Exception){
        Result.failure(e)
    }
}