package com.pdm.fipr.nexusgames.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.nexusgames.data.repositories.gameRepository.GameApiRepository
import com.pdm.fipr.nexusgames.data.repositories.gameRepository.GameRepository
import com.pdm.fipr.nexusgames.data.repositories.wishListRepository.WishListRepository
import com.pdm.fipr.nexusgames.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel()  {
    private val repository : GameRepository = GameApiRepository() // Instanciamos el repositorio
    private val wishListRepository = WishListRepository // Instanciamos el repositorio de favoritos

    private val _apiGame : MutableStateFlow<Game?> = MutableStateFlow(null) // Flujo para el juego de la API
    private val _loading : MutableStateFlow<Boolean> = MutableStateFlow(true) // Flujo para el estado de carga
    private val _error : MutableStateFlow<String?> = MutableStateFlow(null) // Flujo para el estado de error
    private val _isRefreshing : MutableStateFlow<Boolean> = MutableStateFlow(false) // Flujo para el estado de actualización

    val uiState : StateFlow<DetailUiState> = combine( // Combinamos los dos flujos en uno solo
        _apiGame, // Flujo para el juego de la API
        wishListRepository.wishList, // Flujo para la lista de juegos favoritos
        _loading, // Flujo para el estado de carga
        _error, // Flujo para el estado de error
        _isRefreshing // Flujo para el estado de actualización
    ) {apiGame, wishList, loading, errorMsg, isRefreshing ->
        // Revisamos si el juego actual existe en la lista de favoritos (TRUE/FALSE)
        val isFavorite = wishList.any { it.id == apiGame?.id }

        // Empaquetamos todos los datos en nuestro UiState final
        DetailUiState(
            game = apiGame?.copy(isFavorite = isFavorite), // Actualizamos el juego con el estado de favorito
            loading = loading,
            error = errorMsg,
            isRefreshing = isRefreshing
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailUiState(loading = true)
    )


    fun loadGame(id : Int) {
        viewModelScope.launch {
            _loading.value = true
            _apiGame.value = null
            _error.value = null
            repository.getGameById(id)
                .onSuccess { apiGame ->
                    _apiGame.value = apiGame
                    _loading.value = false
                }
                .onFailure { error ->
                    _error.value = error.message ?: "Error desconocido"
                    _loading.value = false
                }
        }
    }

    fun refreshGame(id : Int) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            _isRefreshing.value = true
            repository.getGameById(id)
                .onSuccess { apiGame ->
                    _apiGame.value = apiGame
                    _loading.value = false
                }
                .onFailure { error ->
                    _error.value = error.message ?: "Error desconocido"
                    _loading.value = false
                }
            _isRefreshing.value = false
        }
    }
    fun onWishListChanged(game : Game) {
        wishListRepository.onWishListChanged(game)
    }
    fun isOnWishList(id: Int): Boolean {
        return wishListRepository.isOnWishList(id)
    }

}