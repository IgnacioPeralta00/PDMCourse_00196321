package com.pdm.rankeuca.ui.screens.questionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.pdm.rankeuca.RankeUcaApplication
import com.pdm.rankeuca.data.remote.dto.VoteItemDto
import com.pdm.rankeuca.domain.repositories.OptionRepository
import com.pdm.rankeuca.domain.repositories.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionaryViewModel(
    private val questionRepository: QuestionRepository,
    private val optionRepository: OptionRepository
) : ViewModel() {

    private val _selectedVotes = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val selectedVotes = _selectedVotes.asStateFlow()

    private val _uiState = MutableStateFlow(QuestionaryUiState())
    val uiState: StateFlow<QuestionaryUiState> = _uiState.asStateFlow()

    init {
        fetchQuestionaries()
    }

    fun fetchQuestionaries() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            questionRepository.getQuestionary()
                .onSuccess { list ->
                    _uiState.update { it.copy(questionaries = list, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message, isLoading = false) }
                }
        }
    }

    fun selectOption(questionId: Int, optionId: Int) {
        _selectedVotes.update { current ->
            current + (questionId to optionId)
        }
    }
    fun submitVotes() {
        val votesList = _selectedVotes.value.map { (qId, oId) ->
            VoteItemDto(questionId = qId, optionId = oId)
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            optionRepository.bulkVote(votesList)
                .onSuccess {
                    _selectedVotes.value = emptyMap()
                    fetchQuestionaries()
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message, isLoading = false) }
                }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RankeUcaApplication
                QuestionaryViewModel(
                    app.appProvider.provideQuestionRepository(),
                    app.appProvider.provideOptionRepository()
                )
            }
        }
    }
}