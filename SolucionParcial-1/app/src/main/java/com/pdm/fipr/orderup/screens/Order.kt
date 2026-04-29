package com.pdm.fipr.orderup.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pdm.fipr.orderup.model.Product
import com.pdm.fipr.orderup.screens.components.AppScaffold

@SuppressLint("DefaultLocale")
@Composable
fun OrderResult(
    modifier : Modifier,
    productsOrder: List<Product>,
    onBack: () -> Unit,
    onRowClick: (product: Product) -> Unit,
    onOrderConfirm: () -> Unit
) {

    AppScaffold(
        modifier = Modifier,
        title = "Orden"
    ) { innerPadding ->
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Para eliminar un producto, presione la fila correspondiente",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Producto",
                            modifier = Modifier
                                .weight(3f),
                            fontWeight = FontWeight.Bold)
                        Text(text = "Precio",
                            modifier = Modifier
                                .weight(1.5f),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End)
                        Text(text = "Cant.",
                            modifier = Modifier
                                .weight(1.5f),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End)
                        Text(
                            text = "Total",
                            modifier = Modifier
                                .weight(2f),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End)
                    }
                    HorizontalDivider()
                }
                items(productsOrder) { product ->
                    Row(
                        modifier = Modifier
                            .clickable { onRowClick(product) }
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = product.nombre,
                            modifier = Modifier
                                .weight(3f)
                                .background(
                                    color = androidx.compose.ui.graphics.Color.Transparent
                                ))
                        Text(
                            text = "$${String.format("%.2f", product.precio)}",
                            modifier = Modifier
                                .weight(1.5f),
                            textAlign = TextAlign.End)
                        Text(
                            text = "${product.cantidad}",
                            modifier = Modifier
                                .weight(1.5f),
                            textAlign = TextAlign.End)
                        Text(
                            text = "$${String.format("%.2f", product.precio * product.cantidad)}",
                            modifier = Modifier
                                .weight(2f),
                            textAlign = TextAlign.End)

                    }
                }
                item {
                    HorizontalDivider()
                    Text(
                        text = "$${String.format("%.2f", productsOrder.sumOf { it.precio * it.cantidad })}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.End
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onBack
                ) {
                    Text(
                        text = "Volver a menú"
                    )
                }
                Button(
                    onClick = onOrderConfirm
                ) {
                    Text(
                        text = "Confirmar orden"
                    )
                }
            }
        }
    }
}