package com.pdm.fipr.orderup.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import com.pdm.fipr.orderup.model.Product

sealed class Routes: NavKey {

    @Serializable
    data object Menu: Routes()

    @Serializable
    data class Order(val list: List<Product>): Routes()


}