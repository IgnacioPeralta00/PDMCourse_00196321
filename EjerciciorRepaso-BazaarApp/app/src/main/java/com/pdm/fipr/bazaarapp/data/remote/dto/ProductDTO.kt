package com.pdm.fipr.bazaarapp.data.remote.dto

import com.pdm.fipr.bazaarapp.models.Product
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ProductDTO(
    val id: Int,
    val title : String,
    val price : Double,
    val description : String,
    val category : String,
    @SerialName("image") val imageURL : String,
    val rating : RatingDTO
)

fun ProductDTO.toModel() : Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageURL = imageURL,
        rating = rating.rate
    )
}