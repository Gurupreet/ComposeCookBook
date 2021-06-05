package com.guru.composecookbook.moviesapp.ui.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.guru.composecookbook.moviesapp.ui.details.MovieDetailViewModel
import com.guru.composecookbook.tags.InterestTag

@Composable
fun GenreSection(viewModel: MovieDetailViewModel, movieGenreIdArray: List<Int>?) {
    movieGenreIdArray?.let { movieGenreIds ->
        val genres by viewModel.genresLiveData.observeAsState(emptyList())
        val movieGenres = genres.filter { movieGenreIds.contains(it.id) }.take(3)
        Row {
            movieGenres.forEach {
                InterestTag(text = it.name)
            }
        }
    }
}
