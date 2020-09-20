package com.guru.composecookbook.ui.moviesappmvi.ui

import android.graphics.Bitmap
import androidx.compose.animation.animate
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.moviesappmvi.data.DemoMovieDataProvider
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MoviePagerItem(movie: Movie, isSelected: Boolean, bitmap: MutableState<Bitmap>) {
    val animateHeight = animate(if (isSelected) 600.dp else 360.dp)
    val animateWidth = animate(if (isSelected) 340.dp else 320.dp)
    val animateElevation = if (isSelected) 12.dp else 2.dp
    if (isSelected) {
        bitmap.value = imageResource(id = R.drawable.camelia).asAndroidBitmap()
    }
    val posterFullPath = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
    Card(
        elevation = animate(animateElevation),
        modifier = Modifier
            .preferredWidth(animateWidth)
            .preferredHeight(animateHeight)
            .padding(24.dp)
            .align(Alignment.CenterHorizontally),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.background
    ) {
        Column {
            CoilImage(
                data = posterFullPath,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().preferredHeight(360.dp)
            )
            Text(
                text = movie.title,
                modifier = Modifier.padding(8.dp),
                style = typography.h6
            )
            Text(
                text = movie.overview,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    .weight(1f),
                style = typography.subtitle2
            )
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Get Tickets", modifier = Modifier.padding(8.dp))
            }
        }
    }
}


@Preview
@Composable
fun PreviewMoviePagerItem() {
    val movie = DemoMovieDataProvider.movie
    // MoviePagerItem(movie = movie, pagerState = PagerState(), selectedPage = )
}