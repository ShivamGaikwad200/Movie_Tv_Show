package com.example.movieassignment.model

import com.google.gson.annotations.SerializedName

data class Releases(
    @SerializedName("id")
    val Id: Int,
    @SerializedName("title")
    val Name: String,
    @SerializedName("type")
    val Type: String,
    @SerializedName("imdb_id")
    val IMDB_ID: String,
    @SerializedName("tmdb_id")
    val TMDB_ID: Int,
    @SerializedName("poster_url")
    val Image: String,
    @SerializedName("source_release_date")
    val ReleaseDate: String,
)