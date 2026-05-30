package com.pdm.fipr.nexusgames

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.nexusgames.routes.Routes
import com.pdm.fipr.nexusgames.screens.detail.GameDetailScreen
import com.pdm.fipr.nexusgames.screens.home.HomeScreen
import com.pdm.fipr.nexusgames.screens.wishList.WishListScreen

@Composable
fun MainNavigation() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                HomeScreen(
                    onGameClick = { id ->
                        backStack.add(Routes.Detail(id))
                    },
                    onWishListClick = {
                        backStack.add(Routes.Wishlist)
                    }
                )
            }
            entry<Routes.Detail> { entry ->
                GameDetailScreen(
                    id = entry.id,
                    onBackClick = { backStack.removeLastOrNull() },
                    onWishListClick = {
                        backStack.add(Routes.Wishlist)
                    }
                )
            }
            entry<Routes.Wishlist> {
                WishListScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    onGameClick = { id ->
                        backStack.add(Routes.Detail(id))
                    }
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