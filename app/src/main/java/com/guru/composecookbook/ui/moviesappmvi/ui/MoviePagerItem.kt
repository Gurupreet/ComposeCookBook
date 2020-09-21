package com.guru.composecookbook.ui.moviesappmvi.ui

import androidx.compose.animation.animate
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.moviesappmvi.data.DemoMovieDataProvider
import com.guru.composecookbook.ui.moviesappmvi.data.models.Movie
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MoviePagerItem(movie: Movie, isSelected: Boolean, openMovieDetail: () -> Unit) {
    val animateHeight = animate(if (isSelected) 620.dp else 360.dp)
    val animateWidth = animate(if (isSelected) 340.dp else 320.dp)
    val animateElevation = if (isSelected) 12.dp else 2.dp
    val posterFullPath = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"

    Card(
        elevation = animate(animateElevation),
        modifier = Modifier
            .preferredWidth(animateWidth)
            .preferredHeight(animateHeight)
            .padding(24.dp)
            .clickable(onClick = { openMovieDetail.invoke() })
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = movie.title,
                    modifier = Modifier.padding(8.dp),
                    style = typography.h6
                )
                IconToggleButton(checked = false, onCheckedChange = {}) {
                    Icon(asset = Icons.Default.LibraryAdd)
                }
            }
            Text(
                text = "Release: ${movie.release_date}",
                modifier = Modifier.padding(horizontal = 8.dp),
                style = typography.h6.copy(fontSize = 12.sp)
            )
            Text(
                text = "PG13  •  ${movie.vote_average}/10",
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                style = typography.h6.copy(fontSize = 12.sp, fontWeight = FontWeight.Medium)
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