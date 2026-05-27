package com.pdm.fipr.nexusgames.screens.wishList

import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.nexusgames.screens.components.AppScaffold
import com.pdm.fipr.nexusgames.screens.wishList.components.GameListCard
import kotlinx.coroutines.launch

@Composable
fun WishListScreen(
    viewModel: WishListViewModel = viewModel(),
    onBackClick: () -> Unit,
    onGameClick: (id: Int) -> Unit
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
        title = "Wish List",
        snackBarHostState = snackbarHostState,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = {/**/}) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Wish List"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(games) { game ->
                GameListCard(
                    game = game,
                    onSeeMoreClick = { onGameClick(game.id) },
                    onFavoriteClick = {
                        viewModel.onWishListChanged(game)
                        scope.launch {
                            snackbarHostState.showSnackbar("Juego eliminado de la lista")
                        }
                    }
                )
            }
        }
    }
}