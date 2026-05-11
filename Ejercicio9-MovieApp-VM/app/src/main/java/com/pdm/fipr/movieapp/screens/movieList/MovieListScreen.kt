package com.pdm.fipr.movieapp.screens.movieList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.movieapp.screens.components.AppScaffold
import com.pdm.fipr.movieapp.screens.components.MovieItem

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = viewModel(), // Inyección de dependencias
    onMovieClick: (Int) -> Unit
) {
    val movies by viewModel.movies.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMovies()
    }

    if (loading) {
        AppScaffold(title = "Movies") { padding ->
            CircularProgressIndicator(modifier = Modifier.padding(padding))
        }
        return
    }

    AppScaffold(
        title = "Movies"
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie, onMovieClick)
            }
        }
    }
}