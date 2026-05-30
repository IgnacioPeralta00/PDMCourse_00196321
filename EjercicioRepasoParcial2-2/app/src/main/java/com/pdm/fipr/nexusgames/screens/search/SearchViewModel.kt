package com.pdm.fipr.nexusgames.screens.search

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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val gameRepository : GameRepository = GameApiRepository() // Instancia del repositorio de juegos
    private val wishListRepository = WishListRepository // Instancia del repositorio de lista de deseos/favoritos
    // Flujos
    private val _query = MutableStateFlow("")
    private val _apiGames = MutableStateFlow<List<Game>>(emptyList())
    private val _loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    private val _hasResults = MutableStateFlow(false)
    private val _isRefreshing = MutableStateFlow(false)

    val uiState : StateFlow<SearchUiState> = combine(
        _apiGames,
        wishListRepository.wishList,
        _loading,
        _error,
        _isRefreshing
    ) { apiGames, wishList, loading, error, isRefreshing ->
        val mergedGames = apiGames.map { apiGame ->
            val isFavorite = wishList.any { favGame -> favGame.id == apiGame.id }

            apiGame.copy(isFavorite = isFavorite)
        }

        SearchUiState(
            games = mergedGames,
            loading = loading,
            error = error,
            isRefreshing = isRefreshing
        )
    }
            .combine(_hasResults) {state, hasResults ->
        state.copy(hasResults = hasResults)
    }
            .combine(_query) { state, query ->
        state.copy(query = query)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SearchUiState(loading = true)
        )

    fun searchGames(query: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            _query.value = query
            gameRepository.searchGames(query)
                .onSuccess { apiGames ->
                    _apiGames.value = apiGames
                    _loading.value = false
                    _hasResults.value = apiGames.isNotEmpty()
                }
                .onFailure { error ->
                    _error.value = error.message ?: "Error desconocido"
                    _loading.value = false
                }
        }
    }
    fun cleanSearch() {
        _query.update { "" }
        _apiGames.update { emptyList() }
        _hasResults.update { false }
        _error.update { null }
    }
    fun onWishListChanged(game: Game) {
        wishListRepository.onWishListChanged(game)
    }
    fun isOnWishList(id: Int): Boolean {
        return wishListRepository.isOnWishList(id)
    }
}
