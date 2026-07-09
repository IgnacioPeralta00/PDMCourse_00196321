package com.pdm.rankeuca.domain.repositories

import com.pdm.rankeuca.domain.models.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestions(): Flow<List<Question>>
    suspend fun addQuestion(title: String)
    suspend fun deleteQuestion(question: Question)

    suspend fun updateQuestion(question: Question)
}