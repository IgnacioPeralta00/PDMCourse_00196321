package com.pdm.rankeuca.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {

    @Serializable
    data object Home : Routes()
    @Serializable
    data object Questionary : Routes()
    @Serializable
    data object Questions : Routes()
    @Serializable
    data class Results(val userVotes: Map<Int, Int> = emptyMap()) : Routes()


    @Serializable
    data class Options(val id: Int) : Routes()
}