package com.pdm.fipr.nexusgames.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
    @Serializable
    data object Home : Routes()
    @Serializable
    data class Detail(val id: Int) : Routes()
    @Serializable
    data object Wishlist : Routes()
    @Serializable
    data object Search : Routes()
}