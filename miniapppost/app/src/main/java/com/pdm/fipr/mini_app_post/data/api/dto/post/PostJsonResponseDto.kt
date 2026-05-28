package com.pdm.fipr.mini_app_post.data.api.dto.post

import com.pdm.fipr.mini_app_post.data.api.dto.JsonDto
import kotlinx.serialization.Serializable

@Serializable
data class PostJsonResponseDto(
    val jsonDto: JsonDto
)

@Serializable
data class PostJsonRequestDto(
    val title: String,
    val body: String,
    val userId: Int
)