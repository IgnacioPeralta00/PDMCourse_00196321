package com.pdm.fipr.mini_app_post.data.repositories.jsonRepository

import com.pdm.fipr.mini_app_post.data.api.KtorClient
import com.pdm.fipr.mini_app_post.data.api.dto.JsonDto
import com.pdm.fipr.mini_app_post.data.api.dto.post.PostJsonRequestDto
import com.pdm.fipr.mini_app_post.data.api.dto.toModel
import com.pdm.fipr.mini_app_post.model.Comment
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class JsonApiRepository : JsonRepository {
    override suspend fun getComments(): List<Comment> {
        val response : List<JsonDto> = KtorClient.client.get("posts").body()

        return response.map { jsonDto -> jsonDto.toModel() }
    }

    override suspend fun postComment(comment: Comment): Comment {
        val request = PostJsonRequestDto(
            title = comment.title,
            body = comment.body,
            userId = comment.userId
        )
        val response : JsonDto = KtorClient.client.post("posts") {
            setBody(request)
        }.body()
        return response.toModel()
    }
}