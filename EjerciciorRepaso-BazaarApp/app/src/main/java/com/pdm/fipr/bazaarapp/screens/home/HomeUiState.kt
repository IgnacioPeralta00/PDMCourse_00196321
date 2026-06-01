package com.pdm.fipr.bazaarapp.screens.home

import com.pdm.fipr.bazaarapp.models.Product

data class HomeUiState(
    val productsByCategory: Map<String, List<Product>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)