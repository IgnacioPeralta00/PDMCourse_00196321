package com.pdm.rankeuca.ui.screens.results

import com.pdm.rankeuca.domain.models.Questionary

data class ResultsUiState(
    val results: List<Questionary> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)

