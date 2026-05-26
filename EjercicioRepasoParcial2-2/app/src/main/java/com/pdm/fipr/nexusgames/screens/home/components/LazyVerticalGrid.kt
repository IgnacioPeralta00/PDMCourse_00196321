package com.pdm.fipr.nexusgames.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm.fipr.nexusgames.model.Game

@Composable
fun GamesHomeGrid(
    modifier: Modifier = Modifier,
    games: List<Game>,
    onCardClick: (id: Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells
            .Adaptive(128.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(games) { game ->
            GameCard(game, onCardClick)
        }
    }
}


