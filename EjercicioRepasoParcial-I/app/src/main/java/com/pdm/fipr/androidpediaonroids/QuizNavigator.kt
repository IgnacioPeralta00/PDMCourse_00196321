package com.pdm.fipr.androidpediaonroids

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.androidpediaonroids.routes.Routes
import com.pdm.fipr.androidpediaonroids.screens.Quiz
import com.pdm.fipr.androidpediaonroids.screens.Welcome
import com.pdm.fipr.androidpediaonroids.screens.Result

@Composable
fun MainNavigation(modifier: Modifier) {
    val backStack = rememberNavBackStack(Routes.Welcome)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Welcome> {
                Welcome(
                    modifier = modifier,
                    navigateToQuiz = {
                        index -> backStack.add(Routes.Quiz(index)) }
                )
            }
            entry<Routes.Quiz> {
                Quiz(
                    modifier = modifier,
                    navigateToResults = {
                        score -> backStack.add(Routes.Result(score))
                    }
                )
            }

            entry<Routes.Result> {
                Result(
                    modifier = modifier,
                    score = 0,
                    message = "",
                    navigateToQuiz = {
                        index -> backStack.add(Routes.Quiz(index))
                    }
                )
            }


        }
    )


}