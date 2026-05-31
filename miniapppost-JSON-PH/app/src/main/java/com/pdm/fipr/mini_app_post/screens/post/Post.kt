package com.pdm.fipr.mini_app_post.screens.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.outlined.PermIdentity
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm.fipr.mini_app_post.model.Comment
import com.pdm.fipr.mini_app_post.screens.components.AppScaffold
import com.pdm.fipr.mini_app_post.screens.home.components.CommentCard

@Composable
fun PostsScreen(
    viewModel : PostViewModel = viewModel(),
    onBack : () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val comments = uiState.list
    val loading = uiState.isLoading

    // Estados locales
    var title by rememberSaveable { mutableStateOf("") }
    var body by rememberSaveable() { mutableStateOf("") }
    var userId by rememberSaveable() { mutableStateOf("") }

    if (loading) {
        AppScaffold(title = "Post Json") { padding ->
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
        title = "Post Json",
        navigationIcon = {
            IconButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    ) { innerPadding -> 
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item {
                OutlinedTextField(
                    value = userId,
                    onValueChange = { userId = it },
                    label = { Text(text = "User Id") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    maxLines = 1,
                    leadingIcon = {Icon(imageVector = Icons.Outlined.PermIdentity, contentDescription = "")}
                )
            }
            item {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "Title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    maxLines = 1,
                    leadingIcon = {Icon(imageVector = Icons.Default.Title, contentDescription = "")}
                )
            }
            item {
                OutlinedTextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text(text = "Body") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    leadingIcon = {Icon(imageVector = Icons.Default.DataObject, contentDescription = "")}
                )
                HorizontalDivider()
            }
            
            item { 
                Button(
                    onClick = {
                        viewModel.addComment(
                            Comment(
                                userId = userId.toInt(),
                                title = title,
                                body = body,
                                id = 101
                            )
                        )
                        title = ""
                        body = ""
                        userId = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "Post")
                }
            }
            
            items(comments) { comment ->
                CommentCard(comment)
            }
        }
    }
}