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
            val existingGame = currentList.any { it.id == game.id }
            if (existingGame) {
                currentList.filter { it.id != game.id }
            } else {
                currentList + game.copy(isFavorite = true)
            }
        }
    }

    fun isOnWishList(id: Int): Boolean {
        return _wishList.value.any { it.id == id }
    }

    fun clearWishList() {
        /*_wishList.value = emptyList()*/
        _wishList.update {
            emptyList()
        }
    }

}