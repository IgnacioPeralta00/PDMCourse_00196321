package com.pdm.fipr.movieapp.screens.movieList

import androidx.lifecycle.ViewModel
import com.pdm.fipr.movieapp.dummy.dummyMovies
import com.pdm.fipr.movieapp.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.asStateFlow


class MovieListViewModel: ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()  //  práctica menos segura: val movies: StateFlow<List<Movie>> = _movies

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun loadMovies() {
        _loading.value = true
        _movies.value = dummyMovies
        _loading.value = false
    }

}