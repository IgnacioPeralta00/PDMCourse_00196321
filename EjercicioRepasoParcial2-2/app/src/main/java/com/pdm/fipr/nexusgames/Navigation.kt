package com.pdm.fipr.nexusgames

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.nexusgames.routes.Routes

@Composable
fun MainNavigation() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {

            }
            entry<Routes.Detail> {

            }
            entry<Routes.Wishlist> {

            }
        }
    )

}