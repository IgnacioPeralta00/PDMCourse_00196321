package com.pdm.rankeuca.ui.screens.question

import com.pdm.rankeuca.domain.models.Option

data class QuestionUiState(
    val options : List<Option> = emptyList(),
    val loading : Boolean = false,
    val error : String? = null,
    val isRefreshing : Boolean = false
)
