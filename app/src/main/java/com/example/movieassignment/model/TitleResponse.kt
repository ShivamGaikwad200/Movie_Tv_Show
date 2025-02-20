package com.example.movieassignment.model

import com.google.gson.annotations.SerializedName

data class TitleResponse(
    @SerializedName("title")
    val Title: String,
    @SerializedName("plot_overview")
    val Description: String,
    @SerializedName("type")
    val Type: String,
    @SerializedName("release_date")
    val ReleaseDate: String?,
    @SerializedName("tmdb_id")
    val Title_id: Int?,
    @SerializedName("poster")
    val PosterURL: String?,
)