package com.pdm.rankeuca.data.repositories

import com.pdm.rankeuca.data.local.dao.QuestionDao
import com.pdm.rankeuca.domain.repositories.QuestionRepository
import com.pdm.rankeuca.data.local.entities.QuestionEntity
import com.pdm.rankeuca.data.local.entities.toEntity
import com.pdm.rankeuca.data.local.relations.toModel
import com.pdm.rankeuca.domain.models.Question
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionRepositoryImpl(
    private val questionDao: QuestionDao
) : QuestionRepository {

    override fun getQuestions(): Flow<List<Question>> {
        return questionDao.getQuestionsWithOptions().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun addQuestion(title: String) {
        questionDao.insertQuestion(QuestionEntity(title = title))
    }

    override suspend fun deleteQuestion(question: Question) {
        questionDao.deleteQuestion(question.toEntity())
    }
}