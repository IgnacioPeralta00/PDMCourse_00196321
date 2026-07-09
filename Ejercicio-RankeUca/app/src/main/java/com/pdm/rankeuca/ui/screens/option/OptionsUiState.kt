package com.pdm.rankeuca.ui.screens.option

import com.pdm.rankeuca.domain.models.Option

data class OptionsUiState(
    val options : List<Option> = emptyList(),
    val loading : Boolean = false,
    val error : String? = null,
    val isRefreshing : Boolean = false
)
