package com.pdm.rankeuca.ui.screens.results

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(
    userVotes: Map<Int, Int>,
    onBackClick: () -> Unit,
    viewModel: ResultsViewModel = viewModel(factory = ResultsViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ranking de Resultados") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(uiState.results) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(item.question.title, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(12.dp))

                            val maxVotes = item.options.maxOfOrNull { it.votes ?: 0 }?.coerceAtLeast(1) ?: 1

                            item.options.forEach { option ->
                                val isMyVote = userVotes[item.question.id] == option.id
                                val progress = (option.votes ?: 0).toFloat() / maxVotes

                                Column(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                                        Text(
                                            text = if (isMyVote) "${option.value} (Tu voto)" else option.value,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = if (isMyVote) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isMyVote) MaterialTheme.colorScheme.primary else Color.Unspecified
                                        )
                                        Text("${option.votes ?: 0}", style = MaterialTheme.typography.bodySmall)
                                    }

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(8.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(MaterialTheme.colorScheme.surfaceVariant)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(progress)
                                                .fillMaxHeight()
                                                .background(
                                                    if (isMyVote) MaterialTheme.colorScheme.primary
                                                    else MaterialTheme.colorScheme.secondary
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

