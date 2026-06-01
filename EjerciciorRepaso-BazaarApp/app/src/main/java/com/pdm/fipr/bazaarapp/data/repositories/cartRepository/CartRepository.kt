package com.pdm.fipr.bazaarapp.data.repositories.cartRepository

import com.pdm.fipr.bazaarapp.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update


data class CartItem(
    val product: Product,
    val quantity: Int
)

object CartRepository {

    private val _cartItems = MutableStateFlow<Map<Int, CartItem>>(emptyMap())
    // Exponemos solo la lista al VM
    val cartItems = _cartItems.map {
        it.values.toList()
    }


    fun addToCart(product: Product) {
        _cartItems.update { currentMap ->
            val existingItem = currentMap[product.id]
            if (existingItem != null) {
                val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
                currentMap + (product.id to updatedItem)
            } else {
                currentMap + (product.id to CartItem(product = product, quantity = 1))
            }
        }
    }


    fun deleteFromCart(product: Product) {
        _cartItems.update { currentMap ->
            currentMap - product.id
        }
    }

    fun increaseQuantity(product: Product) {
        _cartItems.update { currentMap ->
            val existingItem = currentMap[product.id]
            if (existingItem != null) {
                val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
                currentMap + (product.id to updatedItem)
            }
            else {
                currentMap + (product.id to CartItem(product = product, quantity = 1))
            }
        }
    }

    fun decreaseQuantity(product: Product) {
        _cartItems.update { currentMap ->
            val existingItem = currentMap[product.id]
            if (existingItem != null && existingItem.quantity > 1) {
                val updatedItem = existingItem.copy(quantity = existingItem.quantity - 1)
                currentMap + (product.id to updatedItem)
            } else if (existingItem != null && existingItem.quantity == 1) {
                currentMap - product.id
            }
            else {
                currentMap - product.id
            }
        }
    }


    fun clearCart() {
        _cartItems.value = emptyMap()
    }



}