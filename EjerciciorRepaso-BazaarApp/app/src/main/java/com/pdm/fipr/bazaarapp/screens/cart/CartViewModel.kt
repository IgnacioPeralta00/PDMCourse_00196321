package com.pdm.fipr.bazaarapp.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.bazaarapp.data.repositories.cartRepository.CartRepository
import com.pdm.fipr.bazaarapp.models.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CartViewModel : ViewModel() {
    private val cartRepository = CartRepository
    val uiState : StateFlow<CartUiState> = cartRepository.cartItems.map { cartItems ->
        val totalPrice = cartItems.sumOf { it.product.price * it.quantity }
        CartUiState(
            cartItems = cartItems,
            totalPrice = totalPrice,
            isLoading = false,
            error = null,
            isRefreshing = false
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CartUiState(isLoading = true)
        )

    fun addToCart(product: Product) {
        cartRepository.AddToCart(product)
    }

    fun clearCart() {
        cartRepository.clearCart()
    }
}