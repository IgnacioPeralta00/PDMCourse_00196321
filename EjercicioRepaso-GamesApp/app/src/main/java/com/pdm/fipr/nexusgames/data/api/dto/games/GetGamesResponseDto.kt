package com.pdm.fipr.nexusgames.data.api.dto.games

import kotlinx.serialization.Serializable

@Serializable
data class GetGamesResponseDto(
    val results : List<GameDTO>
)