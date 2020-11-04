package com.guru.composecookbook.ui.demoui.tiktok

import android.net.Uri
import android.util.Log
import androidx.compose.animation.animate
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onActive
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.drawLayer
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.tiktokRed
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.carousel.Pager
import com.guru.composecookbook.ui.carousel.PagerState
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ContextAmbient
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun HomeScreen() {
    val movies = SpotifyDataProvider.albums
    val bottomBarHeight = 50.dp
    val pagerState: PagerState = run {
        val clock = AnimationClockAmbient.current
        remember(clock) {
            PagerState(clock, 0, 0, movies.size - 1)
        }
    }
    Pager(state = pagerState, orientation = Orientation.Vertical, modifier = Modifier.fillMaxSize().padding(bottom = bottomBarHeight)) {
        val movie = movies[page]
        val isSelected = pagerState.currentPage == page
        PagerItem(movie, isSelected)
    }
}

val videos = listOf("t1.mp4", "t2.mp4", "t3.mp4")

@Composable
fun PagerItem(album: Album, selected: Boolean) {
    val context = ContextAmbient.current

    Box(modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(4.dp))) {
        TikTokPlayer(context, videos[album.id % 3], selected)
        VideoOverLayUI(album)
    }
}

@Composable
fun VideoOverLayUI(album: Album) {
    Row(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        VideoInfoSection(Modifier.weight(1f), album)
        VideoIconsSection(album)
    }
}

@Composable
fun VideoIconsSection(album: Album) {
    var fav by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        ProfileImageWithFollow(modifier = Modifier.preferredSize(64.dp), true, album.imageId)
        Spacer(modifier = Modifier.height(20.dp))
        Icon(
            asset = vectorResource(id = R.drawable.ic_heart_solid),
            modifier = Modifier.clickable(onClick = { fav = !fav }),
            tint = animate(if (fav) tiktokRed else Color.White)
        )
        Text(
            text = "256.4k",
            style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
        )
        Icon(asset = vectorResource(id = R.drawable.ic_comment_dots_solid))
        Text(
            text = "1223",
            style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
        )
        Icon(asset = vectorResource(id = R.drawable.ic_share_solid))
        Text(
            text = "238",
            style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )
        val rotation = animatedFloat(initVal = 0f)
        onActive {
            rotation.animateTo(
                targetValue = 360f,
                anim = repeatable(
                    iterations = AnimationConstants.Infinite,
                    animation = tween(durationMillis = 3500, easing = LinearEasing),
                ),
            )
        }
        ProfileImageWithFollow(
            modifier = Modifier.preferredSize(64.dp).drawLayer(rotationZ = rotation.value),
            false,
            album.imageId
        )
    }
}

@Composable
fun VideoInfoSection(modifier: Modifier, album: Album) {
    Column(modifier = modifier.padding(8.dp)) {
        FilterTag(text = album.genre, modifier = Modifier)
        Text(
            text = "@${album.artist}",
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(text = album.song, style = MaterialTheme.typography.body2)
        Text(
            text = "#${album.artist} #cool #tiktok #videos",
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
fun FilterTag(text: String, modifier: Modifier) {
    val tagModifier = modifier
        .clickable(onClick = {})
        .clip(RoundedCornerShape(4.dp))
        .drawOpacity(0.4f)
        .background(Color.Black)
        .padding(horizontal = 8.dp, vertical = 4.dp)

    Text(
        text = text,
        color = Color.White,
        modifier = tagModifier,
        style = typography.body2.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun ProfileImageWithFollow(modifier: Modifier, showFollow: Boolean, imageId: Int) {
    if (showFollow) {
        Box(modifier = modifier) {
            ImageWithBorder(imageId = imageId, modifier = modifier)
            Icon(
                asset = Icons.Filled.Add,
                modifier = Modifier
                    .preferredSize(20.dp)
                    .clip(CircleShape)
                    .background(tiktokRed).align(Alignment.BottomCenter)
            )
        }
    } else {
        ImageWithBorder(imageId = imageId, modifier = modifier)
    }
}

@Composable
fun ImageWithBorder(imageId: Int, modifier: Modifier) {
    Image(
        asset = imageResource(id = imageId),
        modifier = modifier.padding(8.dp).clip(CircleShape)
            .border(
                shape = CircleShape,
                border = BorderStroke(
                    1.dp,
                    color = Color.White
                )
            )
    )
}
