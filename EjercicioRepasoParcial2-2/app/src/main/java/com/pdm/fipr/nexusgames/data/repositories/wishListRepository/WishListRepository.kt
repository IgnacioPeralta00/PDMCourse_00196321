package com.pdm.fipr.nexusgames.data.repositories.wishListRepository

import com.pdm.fipr.nexusgames.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object WishListRepository {
    private val _wishList = MutableStateFlow(emptyList<Game>())
    val wishList = _wishList.asStateFlow()


    fun onWishListChanged(game : Game) {
        _wishList.update { currentList ->
            if (game.id in currentList.map { game -> game.id }) {
                currentList - game
            } else {
                currentList + game
            }
        }
    }

    fun clearWishList() {
        /*_wishList.value = emptyList()*/
        _wishList.update {
            emptyList()
        }
    }

}