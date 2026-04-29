package com.pdm.fipr.orderup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.orderup.dummy.menu
import com.pdm.fipr.orderup.model.Product
import com.pdm.fipr.orderup.routes.Routes
import com.pdm.fipr.orderup.screens.OrderResult
import com.pdm.fipr.orderup.screens.ProductMenu

@Composable
fun MainNavigator(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(Routes.Menu)
    val productsOrder = remember { mutableStateListOf<Product>() }

    fun onProductClick(product: Product) {

        val productFound = productsOrder.find { it.id == product.id }

        if (productFound !== null) {
            val productIndex = productsOrder.indexOf(productFound)
            productsOrder[productIndex] = productFound.copy(cantidad = productFound.cantidad + 1)
        } else {
            productsOrder.add(product.copy(cantidad = 1))
        }
    }

    fun onRowClick(product: Product) {
        //val productFound = productsOrder.find { it.id == product.id }
        val productIndex = productsOrder.indexOf(product)
        productsOrder.removeAt(productIndex)
    }


    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Menu> {
                ProductMenu(
                    modifier = modifier,
                    productsOrder = productsOrder,
                    onProductClick = { product -> onProductClick(product) },
                    onCheckOrder = { backStack.add(Routes.Order(productsOrder)) }
                )
            }
            entry<Routes.Order> { list ->
                OrderResult(
                    modifier = Modifier,
                    list.list,
                    onBack = { backStack.removeLastOrNull() },
                    onRowClick = { product -> onRowClick(product) }
                )
            }
        }
    )
}