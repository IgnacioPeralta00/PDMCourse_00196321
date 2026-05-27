package com.pdm.fipr.nexusgames.data.repositories.wishListRepository

import com.pdm.fipr.nexusgames.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object WishListRepository {
    private val _wishList = MutableStateFlow(emptyList<Game>())
    val wishList = _wishList.asStateFlow()

    fun addGame(game : Game) {
       /* _wishList.value.toMutableList().add(game)*/
        _wishList.update { currentList ->
            currentList + game
        }
    }
    fun deleteGame(game : Game) {
        /*_wishList.value.toMutableList().remove(game)*/
        _wishList.update { currentList ->
            currentList - game
        }
    }
    fun clearWishList() {
        /*_wishList.value = emptyList()*/
        _wishList.update {
            emptyList()
        }
    }

}