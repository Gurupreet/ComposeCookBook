package com.guru.composecookbook.ui.home.advancelists

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.verticalgrid.VerticalGrid
import com.guru.composecookbook.youtube.components.YoutubeChip

@Composable
fun AnimatedLists() {
  val tweets = DemoDataProvider.tweetList
  val animations = listOf("Fade", "Scale", "Slide", "Fade+Slide", "Slide up", "RotateX")
  Column {
    var animationIndex by remember { mutableStateOf(0) }
    VerticalGrid(columns = 3, modifier = Modifier.padding(vertical = 12.dp)) {
      animations.forEachIndexed { index, title ->
        YoutubeChip(
          selected = index == animationIndex,
          text = title,
          modifier = Modifier.padding(8.dp).clickable(onClick = { animationIndex = index }),
        )
      }
    }
    LazyColumn {
      itemsIndexed(
        items = tweets,
        itemContent = { index, tweet -> AnimatedListItem(tweet = tweet, index, animationIndex) },
      )
    }
  }
}

@Composable
fun AnimatedListItem(tweet: Tweet, itemIndex: Int, animationIndex: Int) {

  val animatedModifier =
    when (animationIndex) {
      0 -> {
        val animatedProgress = remember { Animatable(initialValue = 0f) }
        LaunchedEffect(Unit) {
          animatedProgress.animateTo(targetValue = 1f, animationSpec = tween(600))
        }
        Modifier.padding(8.dp).alpha(animatedProgress.value)
      }
      1 -> {
        val animatedProgress = remember { Animatable(initialValue = 0.8f) }
        LaunchedEffect(Unit) {
          animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(300, easing = LinearEasing),
          )
        }
        Modifier.padding(8.dp)
          .graphicsLayer(scaleY = animatedProgress.value, scaleX = animatedProgress.value)
      }
      2 -> {
        val animatedProgress = remember { Animatable(initialValue = 300f) }
        LaunchedEffect(Unit) {
          animatedProgress.animateTo(
            targetValue = 0f,
            animationSpec = tween(300, easing = FastOutSlowInEasing),
          )
        }
        Modifier.padding(8.dp).graphicsLayer(translationX = animatedProgress.value)
      }
      3 -> {
        val animatedProgress = remember { Animatable(initialValue = -300f) }
        val opacityProgress = remember { Animatable(initialValue = 0f) }
        LaunchedEffect(Unit) {
          animatedProgress.animateTo(
            targetValue = 0f,
            animationSpec = tween(300, easing = LinearEasing),
          )
          opacityProgress.animateTo(targetValue = 1f, animationSpec = tween(600))
        }
        Modifier.padding(8.dp)
          .graphicsLayer(translationX = animatedProgress.value)
          .alpha(opacityProgress.value)
      }
      4 -> {
        val animatedProgress = remember { Animatable(initialValue = 300f) }
        val opacityProgress = remember { Animatable(initialValue = 0f) }
        LaunchedEffect(Unit) {
          animatedProgress.animateTo(
            targetValue = 0f,
            animationSpec = tween(300, easing = LinearEasing),
          )
          opacityProgress.animateTo(targetValue = 1f, animationSpec = tween(600))
        }
        Modifier.padding(8.dp)
          .graphicsLayer(translationY = animatedProgress.value)
          .alpha(opacityProgress.value)
      }
      5 -> {
        val animatedProgress = remember { Animatable(initialValue = 0f) }
        LaunchedEffect(Unit) {
          animatedProgress.animateTo(
            targetValue = 360f,
            animationSpec = tween(400, easing = FastOutSlowInEasing),
          )
        }
        Modifier.padding(8.dp).graphicsLayer(rotationX = animatedProgress.value)
      }
      else -> {
        val animatedProgress = remember { Animatable(initialValue = 0.8f) }
        LaunchedEffect(Unit) {
          animatedProgress.animateTo(targetValue = 1f, animationSpec = tween(300))
        }
        Modifier.padding(8.dp).alpha(animatedProgress.value)
      }
    }
  Row(modifier = animatedModifier, verticalAlignment = Alignment.CenterVertically) {
    Image(
      painter =
        rememberAsyncImagePainter(
          model =
            "https://picsum.photos/id/${
                    itemIndex +
                        1
                }/200/200"
        ),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier.size(55.dp).padding(4.dp),
    )
    Column(modifier = Modifier.padding(horizontal = 4.dp).weight(1f)) {
      Text(
        text = tweet.author,
        style =
          MaterialTheme.typography.titleLarge.copy(
            fontSize = 16.sp,
            // M2 h6 had no lineHeight; resetting it keeps the row height unchanged.
            lineHeight = TextUnit.Unspecified,
          ),
        color = MaterialTheme.colorScheme.onSurface,
      )
      Text(
        text = tweet.text,
        // Legacy subtitle2 was 14sp Normal gray; M3 titleSmall is Medium weight with no color,
        // so reset the weight and keep the gray.
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
        color = Color.Gray,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
    }
    Icon(
      imageVector = Icons.Default.MoreVert,
      contentDescription = null,
      tint = Color.LightGray,
      modifier = Modifier.padding(4.dp),
    )
  }
}
