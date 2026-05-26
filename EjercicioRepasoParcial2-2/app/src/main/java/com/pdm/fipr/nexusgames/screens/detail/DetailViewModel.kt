package com.pdm.fipr.nexusgames.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.nexusgames.data.repositories.gameRepository.GameApiRepository
import com.pdm.fipr.nexusgames.data.repositories.gameRepository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel()  {
    val repository : GameRepository = GameApiRepository()
    private val _uiState = MutableStateFlow(DetailUiState(loading = true))
    val uiState = _uiState.asStateFlow()

    fun loadGame(id : Int) {
        _uiState.update { it.copy(loading = true, game = null) } // Evita datos viejos en el estado al cargar el nuevo detalle
        viewModelScope.launch {
            val game = repository.getGameById(id)
            _uiState.update { state ->
                state.copy(
                    game = game,
                    loading = false
                )
            }
        }
    }

}