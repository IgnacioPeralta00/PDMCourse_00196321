package com.pdm.rankeuca.domain.models

data class Option(
    val id: Int = 0,
    val value: String,
    val imageUrl: String? = null,
    val votes: Int? = null,
    val questionId: Int = 0
)
