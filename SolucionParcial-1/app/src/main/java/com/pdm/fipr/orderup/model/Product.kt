package com.pdm.fipr.orderup.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagenUrl: String,
    val tipo: TipoProducto,
    val cantidad: Int = 0
)

enum class TipoProducto {
    PUPUSA,
    BEBIDA
}