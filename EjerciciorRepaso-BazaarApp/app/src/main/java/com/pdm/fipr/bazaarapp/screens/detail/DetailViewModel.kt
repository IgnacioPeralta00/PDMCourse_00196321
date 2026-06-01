package com.pdm.fipr.bazaarapp.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.bazaarapp.data.repositories.cartRepository.CartRepository
import com.pdm.fipr.bazaarapp.data.repositories.productsRepository.ProductsApiRepository
import com.pdm.fipr.bazaarapp.data.repositories.productsRepository.ProductsRepository
import com.pdm.fipr.bazaarapp.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val productsRepository : ProductsRepository =
        ProductsApiRepository() // Instancia del repositorio
    private val cartRepository = CartRepository // Instancia del repositorio
    private val _uiState = MutableStateFlow(DetailUiState(isLoading = true))
    val uiState : StateFlow<DetailUiState> = _uiState.asStateFlow()


    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true) }
            productsRepository.getProductById(productId)
                .onSuccess { product ->
                    _uiState.update { state ->
                        state.copy(
                            product = product,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { state ->
                        state.copy(
                            error = error.message,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun refresh(productId: Int) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isRefreshing = true) }
            productsRepository.getProductById(productId)
                .onSuccess { product ->
                    _uiState.update { state ->
                        state.copy(
                            product = product,
                            isRefreshing = false
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { state ->
                        state.copy(
                            error = error.message,
                            isRefreshing = false
                        )
                    }
                }
        }
    }

    fun addToCart(product: Product) {
        cartRepository.addToCart(product)
    }
}
