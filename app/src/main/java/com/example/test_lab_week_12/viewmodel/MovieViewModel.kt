package com.example.test_lab_week_12.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_lab_week_12.data.MovieRepository
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchMovies()
                .catch { e ->
                    _error.value = "An error occurred: ${e.message}"
                }
                .collect { list ->
                    // FILTER + SORT (Commit 3)
                    val sorted = list.sortedByDescending { movie ->
                        movie.popularity ?: 0.0
                    }

                    _movies.value = sorted
                }
        }
    }
}
