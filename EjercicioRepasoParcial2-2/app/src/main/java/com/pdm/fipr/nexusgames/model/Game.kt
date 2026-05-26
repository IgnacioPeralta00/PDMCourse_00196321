package com.pdm.fipr.nexusgames.model

data class Game(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val metacriticScore: Int?,
    val releaseDate: String,
    val platforms: List<String>
)