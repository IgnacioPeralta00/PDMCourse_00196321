package com.pdm.fipr.nexusgames.data.repositories.gameRepository

import com.pdm.fipr.nexusgames.data.api.KtorClient
import com.pdm.fipr.nexusgames.data.api.dto.games.GameDTO
import com.pdm.fipr.nexusgames.data.api.dto.games.GetGamesResponseDto
import com.pdm.fipr.nexusgames.data.api.dto.games.toModel
import com.pdm.fipr.nexusgames.model.Game
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.delay

class GameApiRepository: GameRepository  {
    override suspend fun getGames(): Result<List<Game>> {
        try {
            val response : GetGamesResponseDto = KtorClient.client.get("games") {
                parameter("ordering", "-added")
                parameter("metacritic", "94,100")
                parameter("dates", "2000-01-01,2025-12-31")
                parameter("page_size", 50)
            }.body()
            return Result.success(response.results.map { gameDto -> gameDto.toModel() })
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }
    override suspend fun getGameById(id: Int): Result<Game> {
        try {
            val response : GameDTO = KtorClient.client.get("games/$id").body()

            return Result.success(response.toModel())
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun searchGames(query: String): Result<List<Game>> {
        try {
            val response : GetGamesResponseDto = KtorClient.client.get("games") {
                parameter("search", query)
                parameter("ordering", "-added")
                parameter("page_size", 15)
            }.body()
            return Result.success(response.results.map { gameDto -> gameDto.toModel() })
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }
}