package com.pdm.fipr.mini_app_post.screens.home

import com.pdm.fipr.mini_app_post.model.Comment

data class HomeUiState(
    val isLoading: Boolean = false,
    val comments: List<Comment> = emptyList(),
    //val error: String? = null

)
