package com.pdm.rankeuca.ui.screens.questionary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionaryScreen(
    viewModel: QuestionaryViewModel = viewModel(factory = QuestionaryViewModel.Factory),
    onBackClick: () -> Unit,
    onVoteSuccess: (Map<Int, Int>) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedVotes by viewModel.selectedVotes.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Voto Masivo") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        bottomBar = {
            if (selectedVotes.isNotEmpty()) {
                Button(
                    onClick = {
                        val votesToPass = selectedVotes
                        viewModel.submitVotes()
                        onVoteSuccess(votesToPass)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Registrar ${selectedVotes.size} votos")
                }
            }
        }
    ) { innerPadding ->
        if (uiState.isLoading) {
            Box(Modifier
                .fillMaxSize()
                .padding(innerPadding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (uiState.error != null) {
            Column(Modifier
                .fillMaxSize()
                .padding(innerPadding), Arrangement.Center, Alignment.CenterHorizontally) {
                Text("Error: ${uiState.error}")
                Button(onClick = { viewModel.fetchQuestionaries() }) { Text("Reintentar") }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.questionaries) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = item.question.title, style = MaterialTheme.typography.titleLarge)
                            Spacer(modifier = Modifier.height(12.dp))

                            item.options.forEach { option ->
                                val isSelected = selectedVotes[item.question.id] == option.id

                                OutlinedButton(
                                    onClick = { viewModel.selectOption(item.question.id, option.id) },
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Transparent
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                ) {
                                    Text("${option.value} - ${option.votes}")
                                }
                            }

                        }
                    }
                }
            }
        }

    }
}