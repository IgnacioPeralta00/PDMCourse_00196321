package com.pdm.fipr.nexusgames.screens.detail

import com.pdm.fipr.nexusgames.model.Game

data class DetailUiState(
    val game: Game? = null,
    val loading: Boolean = false
)