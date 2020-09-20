package com.guru.composecookbook.ui.moviesappmvi.ui

import androidx.compose.animation.animate
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.carousel.PagerState
import com.guru.composecookbook.ui.moviesappmvi.data.DemoMovieDataProvider
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie

@Composable
fun MoviePagerItem(movie: Movie, isSelected: Boolean) {
    val animateHeight = animate(if (isSelected) 400.dp else 360.dp)
    val animateWidth = animate(if (isSelected) 280.dp else 200.dp)
    val animateElevation = if (isSelected) 12.dp else 2.dp
    Card(
        elevation = animate(animateElevation),
        modifier = Modifier
            .preferredWidth(animateWidth)
            .preferredHeight(animateHeight)
            .padding(24.dp)
            .align(Alignment.CenterHorizontally),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Stack {
            Image(
                asset = imageResource(id = R.drawable.camelia),
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(4.dp)
                    .fillMaxSize()
            )
            Card(modifier = Modifier.align(Alignment.BottomCenter), elevation = 8.dp) {
                Text(
                    text = movie.title,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                    style = typography.body2
                )
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