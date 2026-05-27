package com.pdm.fipr.nexusgames.screens.home

import androidx.activity.result.launch
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.nexusgames.screens.components.AppScaffold
import com.pdm.fipr.nexusgames.screens.home.components.GamesHomeGrid
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onGameClick: (id: Int) -> Unit,
    onWishListClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val games = uiState.games
    val loading = uiState.loading

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if (loading) {
        AppScaffold(title = "NexusGame") { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        return
    }
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
    ) { innerPadding ->
        GamesHomeGrid(
            modifier = Modifier
                .padding(innerPadding),
            games = games,
            onCardClick = onGameClick,
            onFavoriteClick = {
                game -> viewModel.onWishListChanged(game)
                scope.launch {
                    val message = if (!viewModel.isOnWishList(game.id)) "Juego eliminado de favoritos" else "Juego añadido a favoritos"
                    snackbarHostState.showSnackbar(message)
                }
            }
        )
    }
}