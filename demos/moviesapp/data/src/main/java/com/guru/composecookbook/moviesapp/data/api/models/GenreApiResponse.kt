package com.guru.composecookbook.moviesapp.data.api.models

import com.guru.composecookbook.moviesapp.data.db.models.Genre

data class GenreApiResponse(val genres: List<Genre>)