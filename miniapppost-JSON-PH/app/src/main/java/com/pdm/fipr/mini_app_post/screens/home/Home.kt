package com.pdm.fipr.mini_app_post.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.mini_app_post.screens.components.AppScaffold
import com.pdm.fipr.mini_app_post.screens.home.components.CommentCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onNavigateToPost: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val comments = uiState.comments
    val loading = uiState.isLoading
    if (loading) {
        AppScaffold(title = "Json Placeholder") { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        return
    }

    AppScaffold(
        title = "Json Placeholder",
        actions = {
            IconButton(onClick = onNavigateToPost) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {

            items(comments) { comment ->
                //Composable de la tarjeta
                CommentCard(comment)
            }
        }
    }

}