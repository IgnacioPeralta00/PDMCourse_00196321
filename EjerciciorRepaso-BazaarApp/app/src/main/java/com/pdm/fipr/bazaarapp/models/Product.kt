package com.pdm.fipr.bazaarapp.models

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val imageURL: String,
    val rating: Double
)
