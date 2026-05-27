package com.pdm.fipr.nexusgames.screens.detail

import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.pdm.fipr.nexusgames.screens.components.FavoriteButton
import com.pdm.fipr.nexusgames.screens.components.AppScaffold
import kotlinx.coroutines.launch

@Composable
fun GameDetailScreen(
    id : Int,
    viewModel: DetailViewModel = viewModel(),
    onBackClick: () -> Unit,
    onWishListClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = uiState.game
    val loading = uiState.loading

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(id) {
        viewModel.loadGame(id)
    }

    if (loading) {
        AppScaffold(title = "NexusGame") { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        return
    }

    AppScaffold(
        title = game?.title ?: "NexusGame",
        snackBarHostState = snackbarHostState,
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = onWishListClick) {
                Icon(
                    imageVector = Icons.Default.CollectionsBookmark,
                    contentDescription = "Wish List"
                )
            }
        }
    ) { innerPadding ->
        game?.let {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        AsyncImage(
                            model = game.imageUrl,
                            contentDescription = game.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                // Toda la info del juego
                item {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = game.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )

                                FavoriteButton(
                                    isFavorite = game.isFavorite,
                                    onCheckedChange = {
                                        viewModel.onWishListChanged(game)
                                        scope.launch {
                                            val message = if (!viewModel.isOnWishList(game.id)) "Eliminado de la lista" else "¡Añadido a tu lista!"
                                            snackbarHostState.showSnackbar(message)
                                        }
                                    }
                                )
                            }
                        }

                        Text(
                            text = "Metacritic Score: ${game.metacriticScore ?: "N/A"}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = "Release Date: ${game.releaseDate}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = "Platforms: ${game.platforms.joinToString(", ")}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = game.description,
                            style = MaterialTheme.typography.bodyMedium,

                        )
                    }
                }
            }
        }
    }
}