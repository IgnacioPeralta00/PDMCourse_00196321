package com.pdm.fipr.nexusgames.data.repositories.gameRepository

import com.pdm.fipr.nexusgames.model.Game

interface GameRepository {
    suspend fun getGames(): Result<List<Game>>
    suspend fun getGameById(id: Int): Result<Game>
}
