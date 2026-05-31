package com.pdm.fipr.nexusgames.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.nexusgames.screens.components.AppScaffold
import com.pdm.fipr.nexusgames.screens.components.ErrorScreen
import com.pdm.fipr.nexusgames.screens.components.GameListCard
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    onGameClick: (id: Int) -> Unit,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    AppScaffold(
        title = "NexusGames",
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
                viewModel.cleanSearch() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    ) { contentPadding ->
        Column(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
        ) {
            OutlinedTextField(
                value = uiState.query,
                onValueChange = { viewModel.searchGames(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Buscar...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true
            )
            when {
                uiState.query.isEmpty() -> {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.medium
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.medium
                        ),
                        contentAlignment = Alignment.Center) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(48.dp))
                            Text(
                                "Ingrese un término de búsqueda",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                uiState.loading -> {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                uiState.error != null -> {
                    ErrorScreen(
                        onRetryClick = { viewModel.searchGames(uiState.query) },
                        error = uiState.error
                    )
                }
                !uiState.hasResults -> {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No se encontraron resultados", style = MaterialTheme.typography.bodyLarge)
                    }
                }
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(uiState.games) { game ->
                            GameListCard(
                                game = game,
                                onSeeMoreClick = { onGameClick(game.id) },
                                onFavoriteClick = {
                                    viewModel.onWishListChanged(game)
                                    scope.launch {
                                        val message = if (viewModel.isOnWishList(game.id)) "Añadido a favoritos" else "Eliminado de favoritos"
                                        snackbarHostState.showSnackbar(message)
                                    }
                                }
                            )
                        }
                }
            }
            /*if (uiState.query.isEmpty()) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium
                    ),
                    contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(48.dp))
                        Text(
                            "Ingrese un término de búsqueda",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            if (uiState.query.isNotEmpty() && uiState.games.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No se encontraron resultados", style = MaterialTheme.typography.bodyLarge)
                }
            }
            else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(uiState.games) { game ->
                        GameListCard(
                            game = game,
                            onSeeMoreClick = { onGameClick(game.id) },
                            onFavoriteClick = {
                                viewModel.onWishListChanged(game)
                                scope.launch {
                                    val message = if (viewModel.isOnWishList(game.id)) "Añadido a favoritos" else "Eliminado de favoritos"
                                    snackbarHostState.showSnackbar(message)
                                }
                            }
                        )
                    }
                }
            }*/
        }
    }
}
}