package com.pdm.fipr.nexusgames.data.api.dto.platforms

import kotlinx.serialization.Serializable

@Serializable
data class PlatformItemDto (
    val id : Int,
    val name : String
)

@Serializable
data class PlatformContainerDto (
    val platform : PlatformItemDto
)