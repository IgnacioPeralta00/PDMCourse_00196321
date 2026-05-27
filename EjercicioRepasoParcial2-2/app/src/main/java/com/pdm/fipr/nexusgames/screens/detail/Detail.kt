package com.pdm.fipr.nexusgames.screens.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    AsyncImage(
                        model = game.imageUrl,
                        contentDescription = game.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                // Toda la info del juego
                item {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // TARJETA DE TÍTULO Y FAVORITO
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = game.title,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.ExtraBold,
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

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val scoreColor = when {
                                (game.metacriticScore ?: 0) >= 90 -> Color(0xFF4CAF50)
                                (game.metacriticScore ?: 0) >= 75 -> Color(0xFFFFC107)
                                else -> Color(0xFFF44336)
                            }
                            Column {
                                Text(
                                    "METASCORE",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Box(modifier = Modifier
                                    .padding(top = 4.dp)
                                    .border(
                                        2.dp, scoreColor,
                                        RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 10.dp, vertical = 4.dp)) {
                                    Text(
                                        game.metacriticScore?.toString() ?: "N/A",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Black, color = scoreColor)
                                }
                            }
                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    "RELEASE DATE",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(top = 4.dp)
                                ) {
                                    Icon(
                                        Icons.Default.CalendarMonth,
                                        null,
                                        modifier = Modifier
                                            .size(18.dp),
                                        tint = MaterialTheme.colorScheme.primary)

                                    Spacer(Modifier.width(4.dp))

                                    Text(
                                        game.releaseDate,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }

                        HorizontalDivider(thickness = 0.5.dp)

                        // PLATAFORMAS
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                "AVAILABLE ON",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                game.platforms.forEach { platform ->
                                    SuggestionChip(
                                        onClick = {},
                                        label = { Text(platform) })
                                }
                            }
                        }

                        HorizontalDivider(thickness = 0.5.dp)

                        // DESCRIPCIÓN
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                "ABOUT THIS GAME",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = game.description,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 24.sp,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        }
    }
}