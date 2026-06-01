package com.pdm.fipr.bazaarapp.data.remote.dto

import com.pdm.fipr.bazaarapp.models.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val id: Int,
    val title : String,
    val price : Double,
    val description : String,
    val category : String,
    val imageURL : String,
    val rating : RatingContainerDTO
)

fun ProductDTO.toModel() : Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageURL = imageURL,
        rating = rating.rating.rate
    )
}