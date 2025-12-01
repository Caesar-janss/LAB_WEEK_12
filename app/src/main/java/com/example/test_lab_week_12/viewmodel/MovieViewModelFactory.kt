package com.example.test_lab_week_12.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_lab_week_12.data.MovieRepository

class MovieViewModelFactory(
    private val repo: MovieRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(repo) as T
    }
}
