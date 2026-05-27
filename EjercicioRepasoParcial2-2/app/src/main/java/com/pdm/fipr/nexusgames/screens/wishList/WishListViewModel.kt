package com.pdm.fipr.nexusgames.screens.wishList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.nexusgames.data.repositories.wishListRepository.WishListRepository
import com.pdm.fipr.nexusgames.model.Game
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class WishListViewModel : ViewModel() {
    private val repository = WishListRepository
    val uiState : StateFlow<WishListUiState> = repository.wishList.map { games ->
        WishListUiState(
            games = games,
            loading = false
        )
    }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = WishListUiState(loading = true)
        )

    fun addGame(game : Game) {
        repository.addGame(game)
    }
    fun deleteGame(game : Game) {
        repository.deleteGame(game)
    }
    fun clearWishList() {
        repository.clearWishList()
    }

}