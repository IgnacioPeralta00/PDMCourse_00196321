package com.pdm.fipr.bazaarapp.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pdm.fipr.bazaarapp.models.Product
import com.pdm.fipr.bazaarapp.screens.components.ProductCard

@Composable
fun HomeGrid(
    productsByCategory: Map<String, List<Product>>
) {
    LazyVerticalGrid(
        columns = GridCells
            .Adaptive(128.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        productsByCategory.forEach { (category, products) ->
            item(span = { GridItemSpan(maxLineSpan) }) {
                CategoryCard(category = category)
            }
            items(products) { product ->
                ProductCard(
                    product = product,
                    onProductClick = { /*TODO*/ }
                )
            }
        }
    }
}
