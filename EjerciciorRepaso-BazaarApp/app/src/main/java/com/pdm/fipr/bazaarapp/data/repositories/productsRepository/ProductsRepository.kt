package com.pdm.fipr.bazaarapp.data.repositories.productsRepository

import com.pdm.fipr.bazaarapp.models.Product

interface ProductsRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun getProductById(id: Int): Result<Product>
    suspend fun getProductsByCategory(category: String): Result<List<Product>>
    suspend fun getCategories(): Result<List<String>>
}