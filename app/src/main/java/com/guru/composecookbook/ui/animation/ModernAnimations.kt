package com.guru.composecookbook.ui.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.ui.utils.SubtitleText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ModernAnimations() {
  Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
    SubtitleText(subtitle = "Modern Jetpack Compose Animations")
    Spacer(modifier = Modifier.height(16.dp))

    // 1. Like Button Animation with Keyframes
    SubtitleText(subtitle = "Like Button Animation with Keyframes")
    LikeButtonAnimation()
    Divider(modifier = Modifier.padding(vertical = 16.dp))

    // 2. Animated Content with Combined Animations
    SubtitleText(subtitle = "Animated Content with Combined Animations")
    AnimatedCounter()
    Divider(modifier = Modifier.padding(vertical = 16.dp))

    // 3. Shimmer Loading Animation
    SubtitleText(subtitle = "Shimmer Loading Animation")
    ShimmerLoadingAnimation()
    Divider(modifier = Modifier.padding(vertical = 16.dp))

    // 4. Pulse Animation
    SubtitleText(subtitle = "Pulse Animation")
    PulseAnimation()
    Divider(modifier = Modifier.padding(vertical = 16.dp))

    // 5. Wave Loading Animation
    WaveLoadingAnimation()
  }
}

@Composable
fun LikeButtonAnimation() {
  var liked by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()
  var scale by remember { mutableStateOf(1f) }

  IconButton(
    onClick = {
      liked = !liked
      scope.launch {
        scale = 1.3f
        delay(100)
        scale = 1f
      }
    },
    modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale)
  ) {
    Icon(
      Icons.Default.Favorite,
      contentDescription = "Like",
      tint = if (liked) Color.Red else Color.Gray,
      modifier = Modifier.size(24.dp)
    )
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter() {
  var count by remember { mutableStateOf(0) }

  Row(verticalAlignment = Alignment.CenterVertically) {
    Button(onClick = { count++ }) {
      Text("Count: ")
      AnimatedContent(
        targetState = count,
        transitionSpec = {
          slideInVertically { it } + fadeIn() togetherWith
            slideOutVertically { -it } + fadeOut() using
            SizeTransform(clip = false)
        }
      ) { targetCount ->
        Text("$targetCount")
      }
    }
  }
}

@Composable
fun ShimmerLoadingAnimation() {
  val infiniteTransition = rememberInfiniteTransition()
  val translateAnim =
    infiniteTransition.animateFloat(
      initialValue = 0f,
      targetValue = 1000f,
      animationSpec =
        infiniteRepeatable(
          animation = tween(1500, easing = LinearOutSlowInEasing),
          repeatMode = RepeatMode.Restart
        )
    )

  Box(
    modifier =
      Modifier.fillMaxWidth()
        .height(100.dp)
        .clip(MaterialTheme.shapes.medium)
        .background(green200)
        .background(
          brush =
            androidx.compose.ui.graphics.Brush.linearGradient(
              colors = listOf(Color.Transparent, Color.White.copy(alpha = 0.4f), Color.Transparent),
              start = androidx.compose.ui.geometry.Offset(translateAnim.value - 500f, 0f),
              end = androidx.compose.ui.geometry.Offset(translateAnim.value, 500f)
            )
        )
  )
}

@Composable
fun PulseAnimation() {
  val infiniteTransition = rememberInfiniteTransition()
  val scale by
    infiniteTransition.animateFloat(
      initialValue = 1f,
      targetValue = 1.2f,
      animationSpec =
        infiniteRepeatable(
          animation = tween(1000, easing = EaseInOut),
          repeatMode = RepeatMode.Reverse
        )
    )

  Box(
    modifier =
      Modifier.size(60.dp)
        .graphicsLayer(scaleX = scale, scaleY = scale)
        .clip(CircleShape)
        .background(purple)
  )
}

@Composable
fun WaveLoadingAnimation() {
  val waves = 3
  val infiniteTransition = rememberInfiniteTransition()

  Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
    repeat(waves) { index ->
      val delay = index * 100
      val transition by
        infiniteTransition.animateFloat(
          initialValue = 0f,
          targetValue = 1f,
          animationSpec =
            infiniteRepeatable(
              animation =
                keyframes {
                  durationMillis = 1200
                  0f at 0 with LinearOutSlowInEasing
                  1f at 600 with LinearOutSlowInEasing
                  0f at 1200 with LinearOutSlowInEasing
                },
              initialStartOffset = androidx.compose.animation.core.StartOffset(delay)
            )
        )

      Box(
        modifier =
          Modifier.padding(4.dp)
            .size(20.dp)
            .graphicsLayer {
              scaleY = transition
              alpha = transition
            }
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
      )
    }
  }
}
