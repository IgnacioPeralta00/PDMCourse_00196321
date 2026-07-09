package com.pdm.rankeuca.ui.screens.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.pdm.rankeuca.RankeUcaApplication
import com.pdm.rankeuca.domain.repositories.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResultsViewModel(
    private val questionRepository: QuestionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResultsUiState())
    val uiState = _uiState.asStateFlow()

    init { fetchResults() }

    fun fetchResults(isRefresh: Boolean = false) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = !isRefresh, isRefreshing = isRefresh) }
            questionRepository.getQuestionary()
                .onSuccess { data ->
                    val sortedData = data.map { q ->
                        q.copy(options = q.options.sortedByDescending { it.votes ?: 0 })
                    }
                    _uiState.update { it.copy(results = sortedData, isLoading = false, isRefreshing = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message, isLoading = false, isRefreshing = false) }
                }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RankeUcaApplication
                ResultsViewModel(app.appProvider.provideQuestionRepository())
            }
        }
    }
}

