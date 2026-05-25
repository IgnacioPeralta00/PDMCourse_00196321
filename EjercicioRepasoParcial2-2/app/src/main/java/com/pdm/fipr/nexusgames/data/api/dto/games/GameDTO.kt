package com.pdm.fipr.nexusgames.data.api.dto.games

import com.pdm.fipr.nexusgames.data.api.dto.platforms.PlatformContainerDto
import com.pdm.fipr.nexusgames.model.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDTO(
    val id: Int,
    val slug: String,
    val name: String,
    @SerialName("name_original") val nameOriginal: String,
    val metacritic: Int?,
    val released: String?,
    @SerialName("background_image") val backgroundImage: String?,
    val platforms: List<PlatformContainerDto>?,
    @SerialName("description_raw") val description: String?,
)

// Mapper a modelo
fun GameDTO.toModel(): Game {
    return Game(
        id = id,
        title = name,
        description = description ?: "",
        imageUrl = backgroundImage ?: "",
        metacriticScore = metacritic ?: 0,
        releaseDate = released ?: "",
        platforms = platforms?.map { it.platform.name } ?: emptyList()
    )
}










