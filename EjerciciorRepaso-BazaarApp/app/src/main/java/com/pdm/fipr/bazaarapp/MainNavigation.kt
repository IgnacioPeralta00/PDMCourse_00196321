package com.pdm.fipr.bazaarapp

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.bazaarapp.routes.Routes
import com.pdm.fipr.bazaarapp.screens.cart.CartScreen
import com.pdm.fipr.bazaarapp.screens.detail.DetailScreen
import com.pdm.fipr.bazaarapp.screens.home.HomeScreen

@Composable
fun Navigator() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                HomeScreen(
                    onProductClick = { id -> backStack.add(Routes.Detail(id)) },
                    onCartClick = { backStack.add(Routes.Cart) }
                )
            }
            entry<Routes.Detail> { entry ->
                DetailScreen(
                    productId = entry.id,
                    onBackClick = { backStack.removeLastOrNull() }
                )
            }
            entry<Routes.Cart> {
                CartScreen(
                    onBack = { backStack.removeLastOrNull() },
                    onDetail = { id -> backStack.add(Routes.Detail(id)) }
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
        predictivePopTransitionSpec = { _ ->
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