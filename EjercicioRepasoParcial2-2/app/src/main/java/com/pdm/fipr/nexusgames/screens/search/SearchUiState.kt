package com.pdm.fipr.nexusgames.screens.search

import com.pdm.fipr.nexusgames.model.Game

data class SearchUiState(
    val query : String = "",
    val games : List<Game> = emptyList(),
    val loading : Boolean = false,
    val error : String? = null,
    val hasResults : Boolean = false,
    val isRefreshing : Boolean = false
)
