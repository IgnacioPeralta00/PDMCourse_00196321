package com.pdm.fipr.bazaarapp.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm.fipr.bazaarapp.models.Product

@Composable
fun AddAndRemoveButtons(
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = { onIncrease() }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
        IconButton(
            onClick = { onDecrease() }
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null
            )
        }
    }
}