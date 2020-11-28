package com.guru.composecookbook.ui.advancelists

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.Direction
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import kotlin.math.abs

@Composable
fun SwipeableLists() {
    var albums by mutableStateOf(SpotifyDataProvider.albums)
    LazyColumnForIndexed(items = albums) { index, album ->
        SwipeableListItem(index, album) { index ->
            //TODO On Swiped
        }
    }
}

@Composable
fun SwipeableListItem(index: Int, album: Album, onItemSwiped: (Int) -> Unit) {
    Box {
        BackgroundListItem()
        ForegroundListItem(album, index, onItemSwiped)
    }
}

@Composable
fun ForegroundListItem(album: Album, index: Int, onItemSwiped: (Int) -> Unit) {
    val swipeUpperBound = 1200f
    val itemSwipe = animatedFloat(0f)
    val dragObserver = dragObserver(itemSwipe, swipeUpperBound, index, onItemSwiped)
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp).dragGestureFilter(
                dragObserver = dragObserver,
                canDrag = { direction ->
                    direction == Direction.LEFT
                }
            )
            .drawLayer(
                translationX = itemSwipe.value
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            asset = imageResource(id = album.imageId),
            contentScale = ContentScale.Crop,
            modifier = Modifier.preferredSize(55.dp).padding(4.dp)
        )
        Column(modifier = Modifier.padding(horizontal = 4.dp).weight(1f)) {
            Text(
                text = album.song,
                style = typography.h6.copy(fontSize = 16.sp),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "${album.artist}, ${album.descriptions}",
                style = typography.subtitle2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (album.id % 3 == 0) {
            Icon(
                asset = Icons.Default.Favorite,
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.padding(4.dp).preferredSize(20.dp)
            )
        }
        Icon(
            asset = Icons.Default.MoreVert,
            tint = Color.LightGray,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun BackgroundListItem() {
    Row(
        modifier = Modifier
            .background(Color.Red).padding(8.dp).fillMaxWidth().preferredHeight(50.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(asset = Icons.Default.Delete)
        }
        IconButton(onClick = {}) {
            Icon(asset = Icons.Default.Settings)
        }
    }
}

@Composable
fun dragObserver(
    itemSwipe: AnimatedFloat,
    swipeUpperBound: Float,
    index: Int,
    onItemSwiped: (Int) -> Unit
): DragObserver {

    return object : DragObserver {
        override fun onStart(downPosition: Offset) {
            itemSwipe.setBounds(-swipeUpperBound, swipeUpperBound)
        }

        private fun reset() {
            itemSwipe.animateTo(
                0f,
                anim = SpringSpec<Float>(
                    dampingRatio = 0.8f, stiffness = 300f
                )
            )
        }

        override fun onDrag(dragDistance: Offset): Offset {
            itemSwipe.snapTo(itemSwipe.targetValue + dragDistance.x)
            return dragDistance
        }

        override fun onStop(velocity: Offset) {
            if (abs(itemSwipe.targetValue) < 400f) {
                reset()
            } else {
                val animateTo = if (itemSwipe.value > 0) swipeUpperBound else -swipeUpperBound
                itemSwipe.animateTo(
                    animateTo,
                    anim = SpringSpec<Float>(
                        dampingRatio = 0.8f, stiffness = 300f
                    ),
                    onEnd = { _, _ ->
                        // On swiped do something
                        onItemSwiped.invoke(index)
                    }
                )
            }
        }
    }
}