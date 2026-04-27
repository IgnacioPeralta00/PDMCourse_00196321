package com.pdm.fipr.androidpedia20.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {

    @Serializable
    data object Welcome: Routes()

    @Serializable
    data object Quiz : Routes()


    @Serializable
    data class Result (val score: Int) : Routes()
}