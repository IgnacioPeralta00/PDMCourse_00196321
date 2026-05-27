package com.pdm.fipr.nexusgames.screens.wishList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.nexusgames.screens.components.AppScaffold
import com.pdm.fipr.nexusgames.screens.wishList.components.GameListCard

@Composable
fun WishListScreen(
    viewModel: WishListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val games = uiState.games
    val loading = uiState.loading

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
                .padding(innerPadding)
        ) {
            items(games) { game ->
                GameListCard(
                    game = game
                )
            }
        }
    }
}