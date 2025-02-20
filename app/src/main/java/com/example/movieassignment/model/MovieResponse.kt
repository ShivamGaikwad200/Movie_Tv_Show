package com.example.movieassignment.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("releases")
    val releases: List<Releases>
)
