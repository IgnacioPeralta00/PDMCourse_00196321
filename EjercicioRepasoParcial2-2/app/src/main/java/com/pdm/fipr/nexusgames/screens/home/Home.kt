package com.pdm.fipr.nexusgames.screens.home

import androidx.compose.animation.core.copy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.error
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.nexusgames.screens.components.AppScaffold
import com.pdm.fipr.nexusgames.screens.components.ErrorScreen
import com.pdm.fipr.nexusgames.screens.home.components.GamesHomeGrid
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onGameClick: (id: Int) -> Unit,
    onWishListClick: () -> Unit
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