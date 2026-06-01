package com.pdm.fipr.bazaarapp.screens.cart

import com.pdm.fipr.bazaarapp.data.repositories.cartRepository.CartItem

data class CartUiState(
    val cartItems: List<CartItem> = emptyList(),
    val totalPrice: Double = 0.0,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)
