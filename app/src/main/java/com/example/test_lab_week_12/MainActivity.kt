package com.example.test_lab_week_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_lab_week_12.api.RetrofitInstance
import com.example.test_lab_week_12.data.MovieRepository
import com.example.test_lab_week_12.databinding.ActivityMainBinding
import com.example.test_lab_week_12.ui.MovieAdapter
import com.example.test_lab_week_12.viewmodel.MovieViewModel
import com.example.test_lab_week_12.viewmodel.MovieViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(
            MovieRepository(RetrofitInstance.api)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MovieAdapter()

        binding.movieList.layoutManager = LinearLayoutManager(this)
        binding.movieList.adapter = adapter

        viewModel.movies.observe(this) { list ->
            adapter.submitList(list)
        }

        viewModel.error.observe(this) {
            println("ERROR: $it")
        }

        viewModel.loadMovies()
    }
}
