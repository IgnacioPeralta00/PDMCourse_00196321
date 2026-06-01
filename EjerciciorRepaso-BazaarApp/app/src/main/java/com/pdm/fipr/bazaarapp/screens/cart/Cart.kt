package com.pdm.fipr.bazaarapp.screens.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.bazaarapp.screens.cart.components.CartList
import com.pdm.fipr.bazaarapp.screens.components.AppScaffold
import com.pdm.fipr.bazaarapp.screens.components.ErrorScreen

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(),
    onBack : () -> Unit,
    onDetail : (productId : Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    when {
        uiState.isLoading -> {
            AppScaffold(title = "") { padding ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        uiState.error != null -> {
            AppScaffold(
                title = ""
            ) { contentPadding ->
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { /*viewModel.refreshHome()*/ },
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                ) {
                    ErrorScreen(
                        onRetryClick = { /*viewModel.refresh()*/ },
                        error = uiState.error
                    )
                }
            }
        }
        else -> {
            AppScaffold(
                title = "Cart",
                navigationIcon = {
                    IconButton(
                        onClick = { onBack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                }
            ) { contentPadding ->
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { /*viewModel.refresh()*/ },
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                ) {
                    CartList(
                        cartItems = uiState.cartItems,
                        totalPrice = uiState.totalPrice,
                        onIncrease = { product -> viewModel.increaseQuantity(product) },
                        onDecrease = { product -> viewModel.decreaseQuantity(product) },
                        onDelete = { product -> viewModel.deleteFromCart(product) },
                        onViewDetail = { product -> onDetail(product.id) },
                    )
                }
            }
        }
    }
}