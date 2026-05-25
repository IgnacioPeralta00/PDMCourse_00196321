package com.pdm.fipr.nexusgames

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.nexusgames.data.repositories.gameRepository.GameApiRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = GameApiRepository()

    init {
        testRepository()
    }

    private fun testRepository() {
        viewModelScope.launch {
            try {
                val games = repository.getGames()
                // Imprime cuántos elementos trajo
                Log.d("TEST_REPO", "¡Éxito! Cantidad de juegos traídos: ${games.size}")

                // Imprime el contenido del primer juego para verificar los mappers
                if (games.isNotEmpty()) {
                    Log.d("TEST_REPO", "Primer juego parseado: ${games.first()}")
                }
            } catch (e: Exception) {
                // Si algo falló en la serialización o la red, aquí saltará el error
                Log.e("TEST_REPO", "Error fatal en el repositorio", e)
            }
        }
    }
}