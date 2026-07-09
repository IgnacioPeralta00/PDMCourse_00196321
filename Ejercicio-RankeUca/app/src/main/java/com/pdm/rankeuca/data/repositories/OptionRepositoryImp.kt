package com.pdm.rankeuca.data.repositories

import com.pdm.rankeuca.data.local.dao.OptionDao
import com.pdm.rankeuca.data.remote.KtorClient
import com.pdm.rankeuca.data.remote.dto.OptionDto
import com.pdm.rankeuca.data.remote.dto.PostRequestDto
import com.pdm.rankeuca.domain.repositories.OptionRepository
import com.pdm.rankeuca.data.remote.dto.PostResponseDto
import com.pdm.rankeuca.data.remote.dto.toModel
import com.pdm.rankeuca.data.local.entities.toModel
import com.pdm.rankeuca.data.local.entities.toEntity
import com.pdm.rankeuca.data.remote.dto.BulkVoteRequestDto
import com.pdm.rankeuca.data.remote.dto.BulkVoteResponseDto
import com.pdm.rankeuca.data.remote.dto.VoteItemDto
import com.pdm.rankeuca.domain.models.Option
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OptionRepositoryImpl(
    private val optionDao: OptionDao,
    private val ktorClient: KtorClient
) : OptionRepository {
    override suspend fun getPlaces(): Result<List<Option>> {
        try {
            val response : List<OptionDto> = ktorClient.client.get("options").body()
            return Result.success(response.map { placeDto -> placeDto.toModel() })
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }
    override suspend fun bulkVote(votes: List<VoteItemDto>): Result<Boolean> {
        return try {
            val request = BulkVoteRequestDto(votes = votes)
            val response: BulkVoteResponseDto = ktorClient.client.post("parcialtres/votes") {
                setBody(request)
            }.body()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun votePlace(placeId: Int): Result<Boolean> {
        try {
            val request = PostRequestDto(
                optionId = placeId
            )
            val response : PostResponseDto = ktorClient.client.post("vote") {
                setBody(request)
            }.body()
            return Result.success(response.ok)
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override fun getOptions(questionId: Int): Flow<List<Option>> {
        return optionDao.getOptionsForQuestion(questionId).map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun addOption(name: String, imageUrl: String, questionId: Int) {
        val option = Option(value = name, imageUrl = imageUrl, questionId = questionId)
        optionDao.insertOption(option.toEntity())
    }

    override suspend fun deleteOption(option: Option) {
        optionDao.deleteOption(option.toEntity())
    }

    override suspend fun updateOption(option: Option) {
        optionDao.updateOption(option.toEntity())
    }
}