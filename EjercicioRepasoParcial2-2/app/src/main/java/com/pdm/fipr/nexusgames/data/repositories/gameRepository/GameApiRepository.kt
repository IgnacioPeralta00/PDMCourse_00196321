package com.pdm.fipr.nexusgames.data.repositories.gameRepository

import com.pdm.fipr.nexusgames.data.api.KtorClient
import com.pdm.fipr.nexusgames.data.api.dto.games.GameDTO
import com.pdm.fipr.nexusgames.data.api.dto.games.GetGamesResponseDto
import com.pdm.fipr.nexusgames.data.api.dto.games.toModel
import com.pdm.fipr.nexusgames.model.Game
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GameApiRepository: GameRepository  {
    override suspend fun getGames(): List<Game> {
        val response : GetGamesResponseDto = KtorClient.client.get("games") {
            parameter("ordering", "-added")
            parameter("metacritic", "94,100")
            parameter("dates", "2000-01-01,2025-12-31")
            parameter("page_size", 20)
        }.body()

        return response.results.map { gameDto -> gameDto.toModel() }
    }
    override suspend fun getGameById(id: Int): Game {
        val response : GameDTO = KtorClient.client.get("games/$id").body()

        return response.toModel()
    }
}