package com.guru.composecookbook.ui.demoapps.moviesappmvi.data.api.apiresponses

import com.google.gson.annotations.SerializedName
import com.guru.composecookbook.ui.demoapps.moviesappmvi.data.models.Movie

data class MovieListResponse(
    @SerializedName("pages") val pages: Int,
    @SerializedName("results") val movies: List<Movie>
)