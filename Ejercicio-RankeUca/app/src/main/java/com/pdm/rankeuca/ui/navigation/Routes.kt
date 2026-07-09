package com.pdm.rankeuca.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
    @Serializable
    data object Questions : Routes()

    @Serializable
    data class Options(val id: Int) : Routes()
}