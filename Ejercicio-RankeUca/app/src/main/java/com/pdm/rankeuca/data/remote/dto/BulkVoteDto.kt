package com.pdm.rankeuca.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BulkVoteRequestDto(
    val votes: List<VoteItemDto>
)

@Serializable
data class VoteItemDto(
    val questionId: Int,
    val optionId: Int
)

@Serializable
data class BulkVoteResponseDto(
    val updated: List<UpdatedOptionDto>
)

@Serializable
data class UpdatedOptionDto(
    val id: Int,
    val questionId: Int,
    val votes: Int
)