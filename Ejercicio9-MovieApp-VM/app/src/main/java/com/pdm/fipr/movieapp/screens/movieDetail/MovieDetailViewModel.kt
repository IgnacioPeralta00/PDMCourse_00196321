package com.pdm.fipr.movieapp.screens.movieDetail

import androidx.lifecycle.ViewModel
import com.pdm.fipr.movieapp.dummy.dummyMovies
import com.pdm.fipr.movieapp.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieDetailViewModel: ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie = _movie.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun loadMovie(movieId: Int) {
        _loading.value = true
        _movie.value = dummyMovies.find { it.id == movieId }
        _loading.value = false
    }
}