package com.pdm.fipr.movieapp.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
    @Serializable
    data object Movies: Routes()
    @Serializable
    data class MovieDetails(val id: Int): Routes()
}
