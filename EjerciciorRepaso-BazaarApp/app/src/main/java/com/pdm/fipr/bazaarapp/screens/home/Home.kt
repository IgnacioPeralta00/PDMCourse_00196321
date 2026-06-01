package com.pdm.fipr.bazaarapp.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
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
import com.pdm.fipr.bazaarapp.screens.components.AppScaffold
import com.pdm.fipr.bazaarapp.screens.components.ErrorScreen
import com.pdm.fipr.bazaarapp.screens.home.components.HomeGrid

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onProductClick: (id: Int) -> Unit,
    onCartClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            AppScaffold(title = "BazaarApp") { padding ->
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
                title = "BazaarApp"
            ) { contentPadding ->
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { viewModel.refresh() },
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                ) {
                    ErrorScreen(
                        onRetryClick = { viewModel.refresh() },
                        error = uiState.error
                    )
                }
            }
        }
        else -> {
            AppScaffold(
                title = "BazaarApp",
                actions = {
                    IconButton(onClick = { onCartClick() }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCartCheckout,
                            contentDescription = null
                        )
                    }
                }
            ) { contentPadding ->
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { viewModel.refresh() },
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                ) {
                    HomeGrid(
                        productsByCategory = uiState.productsByCategory,
                        onProductClick = onProductClick
                    )
                }
            }
        }
    }


}