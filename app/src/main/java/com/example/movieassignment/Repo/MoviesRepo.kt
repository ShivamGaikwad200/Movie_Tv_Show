package com.example.movieassignment.Repo

import android.util.Log
import com.example.movieassignment.model.Releases
import com.example.movieassignment.model.TitleResponse
import com.example.movieassignment.network.API
import com.example.movieassignment.model.BaseModel
import retrofit2.Response

class MoviesRepo(private val api: API) {
    suspend fun getMovies(): BaseModel<List<Releases>> {
        val response = request { api.getMovies() }
        return when (response) {
            is BaseModel.Success -> BaseModel.Success(response.data.releases)
            is BaseModel.Error -> BaseModel.Error("Error")
            is BaseModel.Loading -> BaseModel.Loading
        }
    }

    suspend fun getMovieDetails(titleId: String): BaseModel<TitleResponse> {
        Log.d("DetailsScreen","Fetching details for Title ID through moviesrepo: $titleId")
        return request { api.getMovieDetails(titleId = titleId) }
    }
}

suspend fun <T> request(request: suspend () -> Response<T>): BaseModel<T> {
    return try {
        val response = request()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Log.d("detailsscreen", "Success: $body")
                BaseModel.Success(body)
            } else {
                Log.e("detailsscreen", "Response body is null")
                BaseModel.Error("Response body is null")
            }
        } else {
            Log.e("detailsscreen", "Error")
            BaseModel.Error(response.errorBody()?.string().toString())
        }
    } catch (e: Exception) {
        Log.e("API Exception", "Exception: ${e.message}")
        BaseModel.Error(e.message.toString())
    }
}
