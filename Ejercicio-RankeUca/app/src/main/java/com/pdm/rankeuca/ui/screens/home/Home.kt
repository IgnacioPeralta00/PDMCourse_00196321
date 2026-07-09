package com.pdm.rankeuca.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onGestorClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("RankeUca") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedCard(
                onClick = onGestorClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Gestor de preguntas", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Administra tus preguntas y opciones locales.")
                }
            }

            ElevatedCard(
                onClick = { },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        "Votar",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Próximamente (Sincronización remota)",
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}