package com.pdm.fipr.nexusgames.data.repositories.gameRepository

import com.pdm.fipr.nexusgames.model.Game

interface GameRepository {
    suspend fun getGames(): List<Game>
    suspend fun getGameById(id: Int): Game?
}
