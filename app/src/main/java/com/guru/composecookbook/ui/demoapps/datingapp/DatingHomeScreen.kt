package com.guru.composecookbook.ui.demoapps.datingapp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoapps.datingapp.components.DraggableCard
import com.guru.composecookbook.ui.demoapps.spotify.data.Album
import com.guru.composecookbook.ui.demoapps.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.verticalGradientBackground
import kotlin.random.Random

@Composable
fun DatingHomeScreen() {
    val configuration = AmbientConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val cardHeight = screenHeight - 200.dp

    Surface(modifier = Modifier.fillMaxSize()) {
        var persons = mutableListOf<Album>()
        persons.addAll(SpotifyDataProvider.albums.take(15))
        val boxModifier = Modifier

        Box(
            modifier = boxModifier.verticalGradientBackground(
                listOf(
                    Color.White,
                    purple.copy(alpha = 0.2f)
                )
            )
        ) {
            var listEmpty = remember { mutableStateOf(false) }
            DatingLoader(modifier = boxModifier)
            persons.forEachIndexed { index, album ->
                DraggableCard(
                    item = album,
                    modifier = Modifier.fillMaxWidth()
                        .preferredHeight(cardHeight)
                        .padding(
                            top = 16.dp + (index + 2).dp,
                            bottom = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    { swipeResult, album ->
                        if (persons.isNotEmpty()) {
                            persons.remove(album)
                            if (persons.isEmpty()) {
                                listEmpty.value = true
                            }
                        }
                    }
                ) {
                    CardContent(album)
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = cardHeight)
                    .alpha(animateFloatAsState(if (listEmpty.value) 0f else 1f).value)
            ) {
                IconButton(
                    onClick = {
                        /* TODO Hook to swipe event */
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .preferredSize(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.background)
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        tint = Color.Gray,
                        contentDescription = null,
                        modifier = Modifier.preferredSize(36.dp)
                    )
                }
                IconButton(
                    onClick = {
                        /* TODO Hook to swipe event */
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .preferredSize(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.background)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.preferredSize(36.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CardContent(album: Album) {
    Column {
        Image(
            bitmap = imageResource(album.imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            Text(
                text = album.artist,
                style = typography.h6,
                modifier = Modifier.padding(end = 8.dp).weight(1f),
                textAlign = TextAlign.Start
            )
            Icon(
                imageVector = Icons.Outlined.Place,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                tint = purple,
                contentDescription = null
            )
            Text(
                text = "${Random.nextInt(1, 15)} km",
                style = typography.body2,
                color = purple
            )
        }
        Text(
            text = "Age: ${Random.nextInt(21, 30)}, Casually browsing..",
            style = typography.subtitle2,
            modifier = Modifier.padding(bottom = 4.dp, start = 16.dp, end = 16.dp)
        )
        Text(
            text = "Miami, Florida",
            style = typography.subtitle2,
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun DatingLoader(modifier: Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize().clip(CircleShape)) {
        //TODO dating loader animation
     //   FloatMultiStateAnimationCircleCanvas(purple, 400f)
        Image(
            bitmap = imageResource(id = R.drawable.adele21),
            modifier = modifier.preferredSize(50.dp).clip(CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun CancelLikeButtons() {

}


enum class SwipeResult {
    ACCEPTED, REJECTED
}