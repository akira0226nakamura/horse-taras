package com.example.horsetaras.model

data class Horse(
    val id: Int,
    val name: String,
    val age: Int,
    val breed: String,
    val images: List<String>,
    val description: String,
    val rating: Float,
    val price: Double
)
