package com.pdm.fipr.bazaarapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RatingDTO(
    val rate: Double,
    val count: Int
)