package com.pdm.fipr.mini_app_post.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.mini_app_post.data.repositories.jsonRepository.JsonApiRepository
import com.pdm.fipr.mini_app_post.data.repositories.jsonRepository.JsonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository : JsonRepository = JsonApiRepository()
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        loadHome()
    }

    fun loadHome() {
        _uiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            val comments = repository.getComments()
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    comments = comments
                )
            }

        }
    }
}