package com.pdm.fipr.bazaarapp.data.repositories.productsRepository

import com.pdm.fipr.bazaarapp.data.remote.KtorClient
import com.pdm.fipr.bazaarapp.data.remote.dto.ProductDTO
import com.pdm.fipr.bazaarapp.data.remote.dto.toModel
import com.pdm.fipr.bazaarapp.models.Product
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductsApiRepository : ProductsRepository {
    override suspend fun getProducts(): Result<List<Product>> {
        try {
            val response : List<ProductDTO> = KtorClient.client.get("products").body()
            return Result.success(response.map { productDTO ->
                productDTO.toModel()
            } )
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getProductById(id: Int): Result<Product> {
        try {
            val response : ProductDTO = KtorClient.client.get("products/$id").body()
            return Result.success(response.toModel())
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>> {
        try {
            val response : List<ProductDTO> = KtorClient.client.get("products/category/$category").body()
            return Result.success(response.map { productDTO ->
                productDTO.toModel()
            } )
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getCategories(): Result<List<String>> {
        try {
            val response : List<String> = KtorClient.client.get("products/categories").body()
            return Result.success(response)
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }

}