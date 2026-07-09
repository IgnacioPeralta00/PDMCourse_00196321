package com.pdm.rankeuca.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequestDto(
    val optionId : Int
)

@Serializable
data class PostResponseDto(
    val ok : Boolean,
    val message : String
)
