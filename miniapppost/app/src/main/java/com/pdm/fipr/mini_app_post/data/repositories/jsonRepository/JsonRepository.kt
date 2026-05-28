package com.pdm.fipr.mini_app_post.data.repositories.jsonRepository

import com.pdm.fipr.mini_app_post.model.Comment

interface JsonRepository {
    suspend fun getComments(): List<Comment>
    suspend fun postComment(comment: Comment): Comment
}