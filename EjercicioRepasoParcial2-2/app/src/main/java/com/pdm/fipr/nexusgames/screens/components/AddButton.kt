package com.pdm.fipr.nexusgames.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddToListButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Add to list",
            tint = androidx.compose.ui.graphics.Color.White
        )
    }
}

/*
@Composable
fun AddToListButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick ,
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Add to list",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}
*/


@Preview(showBackground = true)
@Composable
fun AddToListButtonPreview() {
    AddToListButton(
        onClick = {}
    )
}
