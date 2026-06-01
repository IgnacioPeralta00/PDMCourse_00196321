package com.pdm.fipr.bazaarapp.screens.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm.fipr.bazaarapp.data.repositories.cartRepository.CartItem
import com.pdm.fipr.bazaarapp.models.Product
import com.pdm.fipr.bazaarapp.screens.components.ProductListCard

@Composable
fun CartList(
    cartItems : List<CartItem>,
    totalPrice : Double,
    onIncrease : (product : Product) -> Unit,
    onDecrease : (product : Product) -> Unit,
    onDelete : (product : Product) -> Unit,
    onViewDetail : (product : Product) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(cartItems) { item ->
            ProductListCard(
                item,
                onIncrease = { onIncrease(item.product) },
                onDecrease = { onDecrease(item.product) },
                onDelete = { onDelete(item.product) },
                onViewDetail = { onViewDetail(item.product) },
            )
        }
        // Total del carrito
        item {
            Text(
                text = "Total: $totalPrice €",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}