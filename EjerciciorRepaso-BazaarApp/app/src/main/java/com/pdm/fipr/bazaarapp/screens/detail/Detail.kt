package com.pdm.fipr.bazaarapp.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.bazaarapp.screens.components.AppScaffold
import com.pdm.fipr.bazaarapp.screens.components.ErrorScreen
import com.pdm.fipr.bazaarapp.screens.detail.components.ProductDetail

@Composable
fun DetailScreen(
    productId : Int,
    onBackClick : () -> Unit,
    viewModel : DetailViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    when {
        uiState.isLoading -> {
            AppScaffold(title = "") { paddingValues ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        uiState.error != null -> {
            AppScaffold(
                title = ""
            ) { paddingValues ->
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { viewModel.refresh(productId) },
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    ErrorScreen(
                        onRetryClick = { viewModel.refresh(productId) },
                        error = uiState.error
                    )
                }
            }
        }
        else -> {
            AppScaffold(
                title = "Detalle",
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            ) { paddingValues ->
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { viewModel.refresh(productId) },
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    ProductDetail(
                        product = uiState.product,
                        onAddToCart = { uiState.product?.let { product -> viewModel.addToCart(product) } },
                    )
                }
            }
        }
    }
}