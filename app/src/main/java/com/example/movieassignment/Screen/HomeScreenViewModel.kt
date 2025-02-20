package com.example.movieassignment.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieassignment.Repo.MoviesRepo
import com.example.movieassignment.model.MovieResponse
import com.example.movieassignment.model.Releases
import com.example.movieassignment.model.BaseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeScreenViewModel: ViewModel(), KoinComponent {
    private val repo: MoviesRepo by inject()

    private val _movies = MutableStateFlow<BaseModel<List<Releases>>>(BaseModel.Loading)
    val movies= _movies.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _movies.update { BaseModel.Loading }
            val data = repo.getMovies()
            _movies.update {
                data
            }
        }
    }
}
