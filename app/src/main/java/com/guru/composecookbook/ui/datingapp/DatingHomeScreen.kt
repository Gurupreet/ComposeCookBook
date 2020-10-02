package com.guru.composecookbook.ui.datingapp

import android.util.Log
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.rawDragGestureFilter
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.ui.utils.TitleText
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ConfigurationAmbient
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import kotlin.math.abs

@Composable
fun DatingHomeScreen() {
    Scaffold(
       topBar = { DatingHomeAppbar() }
    ) {
        DatingCardsContent()
    }
}

@Composable
fun DatingHomeAppbar() {
    TopAppBar(
        title = { Text("Dating Celebs") },
        navigationIcon = {
            Icon(
                asset = Icons.Filled.Person,
                tint = purple,
                modifier = Modifier.padding(8.dp)
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icons.Default.Person
            }
        }
    )
}

@Composable
fun DatingCardsContent() {
    val configuration = ConfigurationAmbient.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val cardHeight = screenHeight - 200.dp
    var reload = remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize()) {
        var persons = mutableListOf<Album>()
        persons.addAll(SpotifyDataProvider.albums.take(12))
        reload.value = false
        Stack(modifier = Modifier) {
            persons.forEach {
                DraggableCard(
                    album = it,
                    modifier = Modifier.fillMaxWidth()
                        .preferredHeight(cardHeight)
                        .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                    { swipeResult, album ->
                        if (persons.isNotEmpty()) {
                            persons.remove(album)
                        }
                    }
                ) {
                    Image(
                        asset = imageResource(it.imageId),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun DraggableCard(
    album: Album,
    modifier: Modifier = Modifier,
    onSwiped: (SwipeResult, Album) -> Unit,
    content: @Composable () -> Unit
) {
    val configuration = ConfigurationAmbient.current
    val screenWidth = configuration.screenWidthDp.dp
    val swipeXLeft = -(screenWidth.value*3.2).toFloat()
    val swipeXRight = (screenWidth.value*3.2).toFloat()
    val swipeYTop = -400f
    val swipeYBottom = 400f
    val swipeX = animatedFloat(initVal = 0f)
    val swipeY = animatedFloat(initVal = 0f)
    val tweenTime = 400

    val dragObserver = object : DragObserver {

        override fun onStart(downPosition: Offset) {
            swipeX.setBounds(swipeXLeft, swipeXRight)
            swipeY.setBounds(swipeYTop, swipeYBottom)
            super.onStart(downPosition)
        }

        override fun onStop(velocity: Offset) {
            // if it's swiped 1/3rd
            if (abs(swipeX.targetValue) < abs(swipeXRight)/4) {
                swipeX.animateTo(0f, tween(tweenTime))
            } else {
                if (swipeX.targetValue > 0) {
                    swipeX.animateTo(swipeXRight, tween(tweenTime))
                } else {
                    swipeX.animateTo(swipeXLeft, tween(tweenTime))
                }
            }

            swipeY.animateTo(0f, tween(tweenTime))
            super.onStop(velocity)
        }


        override fun onCancel() {
            swipeX.animateTo(0f, tween(tweenTime))
            swipeX.animateTo(0f, tween(tweenTime))
            super.onCancel()
        }

        override fun onDrag(dragDistance: Offset): Offset {
            swipeX.animateTo(swipeX.targetValue+dragDistance.x)
            swipeY.animateTo(swipeY.targetValue+dragDistance.y)
            return super.onDrag(dragDistance)
        }
    }
    Log.d("swipeX target ", "${swipeX.targetValue}  - ${swipeX.value} -- ${swipeX.isRunning}")
    val rotationFraction = (swipeX.value/60).coerceIn(-40f, 40f)

    if (abs(swipeX.value) < swipeXRight - 50f) {
        Card(
            elevation = 16.dp,
            modifier = modifier.rawDragGestureFilter(dragObserver).drawLayer(
                translationX = swipeX.value,
                translationY = swipeY.value,
                rotationZ = rotationFraction
            ).clip(RoundedCornerShape(16.dp))
        ) {
            content()
        }
    } else {
        Log.d("on swiped called ", "${swipeX.targetValue}  - ${swipeX.value} -- ${swipeX.isRunning}")
        val swipeResult = if (swipeX.value > 0) SwipeResult.ACCEPTED else SwipeResult.REJECTED
        onSwiped(swipeResult, album)
    }
}

enum class SwipeResult {
    ACCEPTED, REJECTED
}