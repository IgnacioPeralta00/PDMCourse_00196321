package com.pdm.fipr.bazaarapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.bazaarapp.data.repositories.productsRepository.ProductsApiRepository
import com.pdm.fipr.bazaarapp.data.repositories.productsRepository.ProductsRepository
import com.pdm.fipr.bazaarapp.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel()  {
    val productsRepository : ProductsRepository = ProductsApiRepository() // Instancia del repositorio
    private val _apiProducts = MutableStateFlow<List<Product>>(emptyList())
    private val _apiCategories = MutableStateFlow<List<String>>(emptyList())
    private val _error = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _isRefreshing = MutableStateFlow(false)
    val uiState : StateFlow<HomeUiState> = combine(
        _apiProducts,
        _apiCategories,
        _error,
        _isLoading,
        _isRefreshing
    ) { apiProducts, apiCategories, error, isLoading, isRefreshing ->

        val productsByCategory = apiCategories.associateWith { category ->
            apiProducts.filter { it.category == category }
        }
        HomeUiState(
            productsByCategory = productsByCategory,
            error = error,
            isLoading = isLoading,
            isRefreshing = isRefreshing
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(isLoading = true)
        )

    init {
        loadHome()
    }
    fun loadHome() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            productsRepository.getProducts()
                .onSuccess { products ->
                    _apiProducts.value = products
                }
                .onFailure { error ->
                    _error.value = error.message
                }
            productsRepository.getCategories()
                .onSuccess { categories ->
                    _apiCategories.value = categories
                }
                .onFailure { error ->
                    _error.value = error.message
                }
            _isLoading.value = false
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isLoading.update { true }
            _error.update { null }
            productsRepository.getProducts()
                .onSuccess { products ->
                    _apiProducts.update { products }
                }
                .onFailure { error ->
                    _error.update { error.message }
                }
            productsRepository.getCategories()
                .onSuccess { categories ->
                    _apiCategories.update { categories }
                }
                .onFailure { error ->
                    _error.update { error.message }
                }
            _isLoading.update { false }
        }
    }
}

/*
viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            val resultProducts = productsRepository.getProducts()
            val resultCategories = productsRepository.getCategories()
            if (resultProducts.isSuccess && resultCategories.isSuccess) {
                val products = resultProducts.getOrThrow()
                val categories = resultCategories.getOrThrow()
                val productsByCategory = categories.associateWith { category ->
                    products.filter { it.category == category }
                }
                _uiState.value = HomeUiState(
                    productsByCategory = productsByCategory,
                    isLoading = false
                )
            }
        }
*/