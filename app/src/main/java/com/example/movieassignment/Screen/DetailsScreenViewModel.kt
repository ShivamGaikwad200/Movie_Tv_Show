package com.example.movieassignment.Screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieassignment.Repo.MoviesRepo
import com.example.movieassignment.model.BaseModel
import com.example.movieassignment.model.TitleResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailsScreenViewModel: ViewModel(),KoinComponent {
    private val repo: MoviesRepo by inject()

    private val _moviedesc = MutableStateFlow<BaseModel<TitleResponse>>(BaseModel.Loading)
    val moviedesc = _moviedesc.asStateFlow()

    fun getMovieDetails(titleid: String) {
        Log.d("DetailsScreenViewModel", "Fetching details for Title ID: $titleid")
        viewModelScope.launch {
            _moviedesc.update { BaseModel.Loading }
            val data = repo.getMovieDetails(titleid)
            _moviedesc.update {
                data
            }
        }
    }
}