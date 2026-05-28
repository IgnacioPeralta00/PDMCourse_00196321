package com.pdm.fipr.mini_app_post.data.api.dto

import com.pdm.fipr.mini_app_post.model.Comment
import kotlinx.serialization.Serializable
@Serializable
data class JsonDto(
    val userId : Int,
    val id : Int? = null,
    val tittle : String,
    val body : String
)

fun JsonDto.toModel() : Comment {
    return Comment(
        id = id ?: 0,
        userId = userId,
        title = tittle,
        body = body
    )
}