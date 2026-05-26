package com.pdm.fipr.nexusgames.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.nexusgames.data.repositories.gameRepository.GameApiRepository
import com.pdm.fipr.nexusgames.data.repositories.gameRepository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository: GameRepository = GameApiRepository()
    private val _uiState = MutableStateFlow(HomeUiState(loading = true))
    val uiState = _uiState.asStateFlow()

    init {
        loadHome()
    }

    fun loadHome() {
        viewModelScope.launch {
            val games = repository.getGames()
            _uiState.update { state ->
                state.copy(
                    games = games,
                    loading = false
                )
            }
        }
    }

}