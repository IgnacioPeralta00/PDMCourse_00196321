package com.pdm.fipr.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.movieapp.routes.Routes

@Composable
fun MainNavigation() {

    val backStack = rememberNavBackStack(Routes.Movies)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Movies> {

            }
            entry<Routes.MovieDetails> {

            }
        }
    )
}