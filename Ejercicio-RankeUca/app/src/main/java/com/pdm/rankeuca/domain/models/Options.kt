package com.pdm.rankeuca.domain.models

data class Option(
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val votes: Int? = null,
    val questionId: Int = 0
)
