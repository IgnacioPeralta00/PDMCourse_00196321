package com.pdm.rankeuca.domain.repositories

import com.pdm.rankeuca.domain.models.Option
import kotlinx.coroutines.flow.Flow

interface OptionRepository {
    suspend fun getPlaces(): Result<List<Option>>

    suspend fun votePlace(placeId: Int): Result<Boolean>

    fun getOptions(questionId: Int): Flow<List<Option>>

    suspend fun addOption(name: String, imageUrl: String, questionId: Int)

    suspend fun deleteOption(option: Option)

    suspend fun updateOption(option: Option)
}