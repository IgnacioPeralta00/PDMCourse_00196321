package com.pdm.rankeuca.ui.screens.questionary

import com.pdm.rankeuca.domain.models.Questionary

data class QuestionaryUiState(
    val questionaries: List<Questionary> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
