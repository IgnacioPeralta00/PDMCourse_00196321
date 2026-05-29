package com.pdm.fipr.mini_app_post.screens.post

import com.pdm.fipr.mini_app_post.model.Comment

data class PostUiState(
    val list: List<Comment> = emptyList(),
    val isLoading: Boolean = false,
    //val error: String? = null
)
