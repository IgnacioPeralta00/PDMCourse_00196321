package com.pdm.fipr.nexusgames.screens.home

import com.pdm.fipr.nexusgames.model.Game

data class HomeUiState(
    val games: List<Game> = emptyList(),
    val loading : Boolean = false,
    val error : String? = null,
    val isRefreshing : Boolean = false
)