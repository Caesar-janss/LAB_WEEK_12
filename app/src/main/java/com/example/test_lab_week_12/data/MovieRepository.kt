package com.example.test_lab_week_12.data

import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie

class MovieRepository(private val api: MovieService) {

    suspend fun getPopularMovies(): List<Movie> {
        return api.getPopularMovies().results
    }
}
