package com.pdm.fipr.bazaarapp.screens.cart.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.pdm.fipr.bazaarapp.data.repositories.cartRepository.CartItem
import com.pdm.fipr.bazaarapp.screens.components.ProductListCard

@Composable
fun CartList(
    cartItems : List<CartItem>
) {
    LazyColumn() {
        items(cartItems) { item ->
            ProductListCard(
                item,
                onIncrease = {},
                onDecrease = {},
                onDelete = {},
                onViewDetail = { },
            )
        }
    }
}