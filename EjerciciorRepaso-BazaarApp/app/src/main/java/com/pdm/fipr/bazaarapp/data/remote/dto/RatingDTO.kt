package com.pdm.fipr.bazaarapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RatingDTO(
    val rate: Double,
    val count: Int

)
@Serializable
data class RatingContainerDTO(
    val rating: RatingDTO
)