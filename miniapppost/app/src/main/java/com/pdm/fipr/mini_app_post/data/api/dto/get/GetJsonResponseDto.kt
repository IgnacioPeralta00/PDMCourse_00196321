package com.pdm.fipr.mini_app_post.data.api.dto.get

import com.pdm.fipr.mini_app_post.data.api.dto.JsonDto
import kotlinx.serialization.Serializable

@Serializable
data class GetJsonResponseDto(
    val results: List<JsonDto>
)