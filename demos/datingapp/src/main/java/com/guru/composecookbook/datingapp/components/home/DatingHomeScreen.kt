package com.guru.composecookbook.datingapp.components.home

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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.canvas.MultiStateAnimationCircleFilledCanvas
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.R
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.theme.modifiers.verticalGradientBackground
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.theme.typography
import kotlin.random.Random

@Composable
fun DatingHomeScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val cardHeight = screenHeight - 200.dp

    Surface(modifier = Modifier.fillMaxSize()) {
        val persons = mutableListOf<Album>()
        persons.addAll(AlbumsDataProvider.albums.take(15))
        val boxModifier = Modifier

        Box(
            modifier = boxModifier.verticalGradientBackground(
                listOf(
                    Color.White,
                    purple.copy(alpha = 0.2f)
                )
            )
        ) {
            val listEmpty = remember { mutableStateOf(false) }
            DatingLoader(modifier = boxModifier)
            persons.forEachIndexed { index, album ->
                DraggableCard(
                    item = album,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardHeight)
                        .padding(
                            top = 16.dp + (index + 2).dp,
                            bottom = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    onSwiped = { _, swipedAlbum ->
                        if (persons.isNotEmpty()) {
                            persons.remove(swipedAlbum)
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
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.background)
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        tint = Color.Gray,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                }
                IconButton(
                    onClick = {
                        /* TODO Hook to swipe event */
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.background)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(36.dp)
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
            painter = painterResource(album.imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            Text(
                text = album.artist,
                style = typography.h6,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f),
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
    Box(
        contentAlignment = Alignment.Center, modifier = modifier
            .fillMaxSize()
            .clip(CircleShape)
    ) {
        MultiStateAnimationCircleFilledCanvas(purple, 400f)
        Image(
            painter = painterResource(id = R.drawable.adele21),
            modifier = modifier
                .size(50.dp)
                .clip(CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}
