package com.pdm.fipr.studentprofile.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {

    @Serializable
    data object Home: Routes()

    @Serializable
    data class StudentDetail(val id: Int) : Routes()

}