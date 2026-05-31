package com.pdm.fipr.mini_app_post.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
    @Serializable
    data object Home : Routes()
    @Serializable
    data object Post : Routes()
}