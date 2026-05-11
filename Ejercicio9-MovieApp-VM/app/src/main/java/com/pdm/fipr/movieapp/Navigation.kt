package com.pdm.fipr.movieapp

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.movieapp.routes.Routes
import com.pdm.fipr.movieapp.screens.movieDetail.DetailScreen
import com.pdm.fipr.movieapp.screens.movieDetail.DetailScreenV2
import com.pdm.fipr.movieapp.screens.movieList.MovieListScreen

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
                DetailScreenV2(
                    movieId = movieId.id,
                    navigateBack = { backStack.removeLastOrNull() }
                )
            }
        },
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(500)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(500)
            )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(500)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(500)
            )
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(250)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(250)
            )
        }
    )
}