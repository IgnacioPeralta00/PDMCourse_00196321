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
    private val gameRepository: GameRepository = GameApiRepository()
    private val wishListRepository = WishListRepository
    private val _apiGames = MutableStateFlow<List<Game>>(emptyList()) // Flujo para los juegos de la API
    private val _isLoading = MutableStateFlow(true) // Flujo para el estado de carga
    private val _error = MutableStateFlow<String?>(null) // Flujo para el estado de error
    private val _isRefreshing = MutableStateFlow(false) // Flujo para el estado de actualización


    val uiState: StateFlow<HomeUiState> = combine(
        _apiGames,
        wishListRepository.wishList,
        _isLoading,
        _error,
        _isRefreshing
    ) { apiGames, wishList, isLoading, errorMsg, isRefreshing ->

        val mergedGames = apiGames.map { apiGame ->
            val isFavorite = wishList.any { favGame -> favGame.id == apiGame.id }

            apiGame.copy(isFavorite = isFavorite)
        }

        HomeUiState(
            games = mergedGames,
            loading = isLoading,
            error = errorMsg,
            isRefreshing = isRefreshing
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(loading = true)
    )

    init {
        loadHome()
    }
    fun loadHome() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            gameRepository.getGames()
                .onSuccess { apiGames ->
                    _apiGames.value = apiGames
                    _isLoading.value = false
                }
                .onFailure { error ->
                    _error.value = error.message ?: "Error desconocido"
                    _isLoading.value = false
                }
        }
    }

    fun refreshHome() {
        viewModelScope.launch {
            _error.value = null
            _isRefreshing.value = true
            _isLoading.value = true
            gameRepository.getGames()
                .onSuccess { apiGames ->
                    _apiGames.value = apiGames
                    _isLoading.value = false
                }
                .onFailure { error ->
                    _error.value = error.message ?: "Error desconocido"
                    _isLoading.value = false
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