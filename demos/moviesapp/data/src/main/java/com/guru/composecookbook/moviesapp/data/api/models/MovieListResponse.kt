package com.guru.composecookbook.moviesapp.data.api.models

import com.google.gson.annotations.SerializedName
import com.guru.composecookbook.moviesapp.data.db.models.Movie

data class MovieListResponse(
    @SerializedName("pages") val pages: Int,
    @SerializedName("results") val movies: List<Movie>
)