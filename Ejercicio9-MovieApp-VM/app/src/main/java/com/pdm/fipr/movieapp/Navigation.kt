package com.pdm.fipr.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.movieapp.routes.Routes
import com.pdm.fipr.movieapp.screens.DetailScreen
import com.pdm.fipr.movieapp.screens.MovieListScreen

@Composable
fun MainNavigation() {

    val backStack = rememberNavBackStack(Routes.Movies)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Movies> {
                MovieListScreen(
                    onMovieClick = { movieId ->
                        backStack.add(Routes.MovieDetails(movieId))
                    }
                )

            }
            entry<Routes.MovieDetails> { movieId ->
                DetailScreen(movieId = movieId.id)
            }
        }
    )
}