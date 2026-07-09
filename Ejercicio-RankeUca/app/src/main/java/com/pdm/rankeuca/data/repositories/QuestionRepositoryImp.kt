package com.pdm.rankeuca.data.repositories

import com.pdm.rankeuca.data.local.dao.QuestionDao
import com.pdm.rankeuca.domain.repositories.QuestionRepository
import com.pdm.rankeuca.data.local.entities.QuestionEntity
import com.pdm.rankeuca.data.local.entities.toEntity
import com.pdm.rankeuca.data.local.relations.toModel
import com.pdm.rankeuca.data.remote.KtorClient
import com.pdm.rankeuca.domain.models.Question
import com.pdm.rankeuca.domain.models.Questionary
import com.pdm.rankeuca.data.remote.dto.QuestionaryDto
import com.pdm.rankeuca.data.remote.dto.toModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionRepositoryImpl(
    private val questionDao: QuestionDao,
    private val ktorClient: KtorClient
) : QuestionRepository {

    override fun getQuestions(): Flow<List<Question>> {
        return questionDao.getQuestionsWithOptions().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun getQuestionary(): Result<List<Questionary>> {
        return try {
            val response: List<QuestionaryDto> = ktorClient.client.get("parcialtres/questions").body()
            Result.success(response.map { it.toModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addQuestion(title: String) {
        questionDao.insertQuestion(QuestionEntity(title = title))
    }

    override suspend fun deleteQuestion(question: Question) {
        questionDao.deleteQuestion(question.toEntity())
    }

    override suspend fun updateQuestion(question: Question) {
        questionDao.updateQuestion(question.toEntity())
    }
}