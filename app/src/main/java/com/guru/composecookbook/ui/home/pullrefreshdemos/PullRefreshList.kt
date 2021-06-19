package com.guru.composecookbook.ui.home.pullrefreshdemos


import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.guru.composecookbook.data.AlbumsDataProvider

@Composable
fun PullRefreshList(onPullRefresh: () -> Unit) {
    val albums = AlbumsDataProvider.albums
    //TODO revisit pull refresh
    val initialYTranslate = -100f
    val maximumYTranslate = 200f
    val animatedProgress = remember { Animatable(0f) }
    val lazyListState = rememberLazyListState()

//    val draggableModifier = Modifier.draggable(
//        orientation = Orientation.Vertical,
//        reverseDirection = false,
//        enabled = lazyListState.firstVisibleItemIndex < 2,
//        onDragStarted = {
//            onPullRefresh.invoke()
//            if (animatedProgress.value < maximumYTranslate) {
//                animatedProgress.animateTo(
//                    targetValue = maximumYTranslate,
//                    animationSpec = tween(durationMillis = 1200, easing = LinearEasing),
//                )
//            }
//        },
//        onDragStopped = {
//            animatedProgress.animateTo(
//                targetValue = 3000f,
//                animationSpec = repeatable(
//                    iterations = 1,
//                    animation = tween(durationMillis = 3000, easing = LinearEasing),
//                ),
//            )
//        }
//    )

//    Box(modifier = draggableModifier) {
//        LazyColumn(state = lazyListState) {
//            items(
//                count = albums.size,
//                itemContent = { index ->
//                    SpotifySongListItem(album = albums[index])
//                })
//        }
//        //Animated Icon
//        Icon(
//            imageVector = Icons.Default.RotateRight,
//            tint = Color.Black,
//            contentDescription = null,
//            modifier = Modifier.align(Alignment.TopCenter)
//                .graphicsLayer(
//                    translationY = animateFloatAsState(
//                        animatedProgress.value.coerceIn(
//                            initialYTranslate,
//                            maximumYTranslate
//                        )
//                    ).value,
//                    rotationZ = animateFloatAsState(
//                        animatedProgress.value
//                    ).value
//                ).background(Color.LightGray, shape = CircleShape).padding(2.dp)
//        )
//    }
}
