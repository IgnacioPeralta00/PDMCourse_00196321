package com.pdm.fipr.bazaarapp.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey  {
    @Serializable
    data object Home : Routes()
    @Serializable
    data class Detail(val id: Int) : Routes()
    @Serializable
    data object Cart : Routes()
}