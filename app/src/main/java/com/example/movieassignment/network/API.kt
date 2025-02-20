package com.example.movieassignment.network

import com.example.movieassignment.model.MovieResponse
import com.example.movieassignment.model.TitleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val APIKEY= "76sL4LUw86LIgr7Np5QsB6GsQx6UUbz4ypfolFqy"


interface API {
    @GET("releases/")
    suspend fun getMovies(
        @Query("apiKey") apikey : String = APIKEY
    ): Response<MovieResponse>

    @GET("title/{title_id}/details/")
    suspend fun getMovieDetails(
        @Path("title_id") titleId: String,
        @Query("apiKey") apikey: String = APIKEY
    ): Response<TitleResponse>

}
