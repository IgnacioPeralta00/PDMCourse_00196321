package com.pdm.fipr.mini_app_post.model

data class Comment(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)