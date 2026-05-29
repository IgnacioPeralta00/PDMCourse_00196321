package com.pdm.fipr.nexusgames.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.nexusgames.data.repositories.gameRepository.GameApiRepository
import com.pdm.fipr.nexusgames.data.repositories.gameRepository.GameRepository
import com.pdm.fipr.nexusgames.data.repositories.wishListRepository.WishListRepository
import com.pdm.fipr.nexusgames.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository: GameRepository = GameApiRepository()
    private val wishListRepository = WishListRepository

    // Creamos un tanque privado SOLO para los juegos crudos que vienen de la API
    private val _apiGames = MutableStateFlow<List<Game>>(emptyList())
    // Y un tanque para manejar si está cargando o no
    private val _isLoading = MutableStateFlow(true)

    // Combinamos los juegos de la API con la tubería de Favoritos
    val uiState: StateFlow<HomeUiState> = combine(
        _apiGames,
        wishListRepository.wishList,
        _isLoading
    ) { apiGames, wishList, isLoading ->

        // Por cada juego de la API, revisamos si su ID existe en la lista de favoritos
        val mergedGames = apiGames.map { apiGame ->
        // Revisamos si el juego actual existe en la lista de favoritos
            val isFavorite = wishList.any { favGame -> favGame.id == apiGame.id }

            // Creamos una copia del juego actualizando su estado de favorito
            apiGame.copy(isFavorite = isFavorite)
        }

        // Empaquetamos todo en nuestro UiState final
        HomeUiState(
            games = mergedGames,
            loading = isLoading
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(loading = true)
    )

    init {
        loadHome()
    }

    // La función load ahora solo actualiza el tanque de la API
    private fun loadHome() {
        viewModelScope.launch {
            _isLoading.value = true
            val games = repository.getGames()
            _apiGames.value = games
            _isLoading.value = false
        }
    }

    fun onWishListChanged(game : Game) {
        wishListRepository.onWishListChanged(game)
    }

    fun isOnWishList(id: Int): Boolean {
        return wishListRepository.isOnWishList(id)
    }
}