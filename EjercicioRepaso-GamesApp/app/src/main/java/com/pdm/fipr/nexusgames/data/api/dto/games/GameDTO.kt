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
    @SerialName("name_original") val nameOriginal: String? = null,
    val metacritic: Int? = null,
    val released: String? = null,
    @SerialName("background_image") val backgroundImage: String? = null,
    val platforms: List<PlatformContainerDto>? = null,
    @SerialName("description_raw") val description: String? = null,
)
// Mapper a modelo
fun GameDTO.toModel(): Game {
    return Game(
        id = id,
        title = name,
        description = description ?: "",
        imageUrl = backgroundImage ?: "",
        metacriticScore = metacritic,
        releaseDate = released ?: "",
        platforms = platforms?.map { it.platform.name } ?: emptyList(),
        isFavorite = false
    )
}










