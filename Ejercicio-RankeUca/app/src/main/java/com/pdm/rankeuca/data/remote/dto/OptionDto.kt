package com.pdm.rankeuca.data.remote.dto

import com.pdm.rankeuca.domain.models.Option
import kotlinx.serialization.Serializable

@Serializable
data class OptionDto(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int
)

fun OptionDto.toModel() = Option(
    id = id,
    value = name,
    imageUrl = imageUrl,
    votes = votes
)