package com.pdm.fipr.nexusgames.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.nexusgames.screens.components.AppScaffold
import com.pdm.fipr.nexusgames.screens.components.ErrorScreen
import com.pdm.fipr.nexusgames.screens.home.components.GamesHomeGrid
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onGameClick: (id: Int) -> Unit,
    onWishListClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    when {
        uiState.loading -> {
            AppScaffold(title = "NexusGames") { padding ->
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
                title = "NexusGames"
            ) { contentPadding ->
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { viewModel.refreshHome() },
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                ) {
                    ErrorScreen(
                        onRetryClick = { viewModel.refreshHome() },
                        error = uiState.error
                    )
                }
            }
        }
        else -> {
            AppScaffold(
                title = "NexusGames",
                snackBarHostState = snackbarHostState,
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search"
                        )
                    }
                    IconButton(onClick = onWishListClick) {
                        Icon(
                            imageVector = Icons.Default.CollectionsBookmark,
                            contentDescription = "Wish List"
                        )
                    }
                }
            ) { contentPadding ->
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { viewModel.refreshHome() },
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                ) {
                    GamesHomeGrid(
                        games = uiState.games,
                        onCardClick = onGameClick,
                        onFavoriteClick = {
                                game -> viewModel.onWishListChanged(game)
                            scope.launch {
                                val message = if (viewModel.isOnWishList(game.id)) "Juego añadido a favoritos" else "Juego eliminado de favoritos"
                                snackbarHostState.showSnackbar(message)
                            }
                        }
                    )
                }
            }
        }
    }
}