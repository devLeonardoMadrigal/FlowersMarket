package com.example.flowersmarket.model

data class FlowersResponseItem(
    val category: String,
    val instructions: String,
    val name: String,
    val photo: String,
    val price: Double,
    val productId: Int
)