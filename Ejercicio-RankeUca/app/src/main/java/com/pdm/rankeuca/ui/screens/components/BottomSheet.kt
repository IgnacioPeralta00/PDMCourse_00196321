package com.pdmcourse2026.RankeUca.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onSaveForOption: (name: String, imageUrl: String) -> Unit,
    onSaveForQuestion: (title: String) -> Unit,
    isOption: Boolean = true,
    onDismiss: () -> Unit
) {
    if (isOption) {
        var name by rememberSaveable { mutableStateOf("") }
        var imageUrl by rememberSaveable { mutableStateOf("") }

        BaseBottomSheet(
            title = "Nueva opción",
            subtitle = "Agrega nombre e imagen para la lista.",
            onDismiss = onDismiss,
            onSave = { onSaveForOption(name.trim(), imageUrl.trim()) },
            saveEnabled = name.isNotBlank() && imageUrl.isNotBlank()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("URL Imagen") },
                modifier = Modifier.fillMaxWidth())
        }
    } else {
        var title by rememberSaveable { mutableStateOf("") }

        BaseBottomSheet(
            title = "Nueva Pregunta",
            subtitle = "Agrega el título de la pregunta.",
            onDismiss = onDismiss,
            onSave = { onSaveForQuestion(title.trim()) },
            saveEnabled = title.isNotBlank()
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier
                    .fillMaxWidth())
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BaseBottomSheet(
    title: String,
    subtitle: String,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    saveEnabled: Boolean,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            content()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("Cancelar")
                }
                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onSave,
                    enabled = saveEnabled
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}