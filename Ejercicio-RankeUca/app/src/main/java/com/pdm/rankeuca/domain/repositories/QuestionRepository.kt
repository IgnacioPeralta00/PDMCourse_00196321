package com.pdm.rankeuca.domain.repositories

import com.pdm.rankeuca.domain.models.Question
import com.pdm.rankeuca.domain.models.Questionary
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestions(): Flow<List<Question>>
    suspend fun getQuestionary(): Result<List<Questionary>>
    suspend fun addQuestion(title: String)
    suspend fun deleteQuestion(question: Question)

    suspend fun updateQuestion(question: Question)
}