package com.pdm.fipr.bazaarapp.screens.detail

import com.pdm.fipr.bazaarapp.models.Product

data class DetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)
