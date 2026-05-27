package com.pdm.fipr.nexusgames.screens.wishList

import com.pdm.fipr.nexusgames.model.Game

data class WishListUiState(
    val games: List<Game> = emptyList(),
    val loading : Boolean = false
)