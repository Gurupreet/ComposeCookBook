package com.guru.composecookbook.ui.home.advancelists

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class ShimmerAnimationType {
  FADE,
  TRANSLATE,
  FADETRANSLATE,
  VERTICAL
}

@Preview
@Composable
fun ShimmerList() {
  var shimmerAnimationType by remember { mutableStateOf(ShimmerAnimationType.FADE) }

  val transition = rememberInfiniteTransition()
  val translateAnim by
    transition.animateFloat(
      initialValue = 100f,
      targetValue = 600f,
      animationSpec =
        infiniteRepeatable(tween(durationMillis = 1200, easing = LinearEasing), RepeatMode.Restart)
    )

  val colorAnim by
    transition.animateColor(
      initialValue = Color.LightGray.copy(alpha = 0.6f),
      targetValue = Color.LightGray,
      animationSpec =
        infiniteRepeatable(
          tween(durationMillis = 1200, easing = FastOutSlowInEasing),
          RepeatMode.Restart
        )
    )

  val list =
    if (shimmerAnimationType != ShimmerAnimationType.TRANSLATE) {
      listOf(colorAnim, colorAnim.copy(alpha = 0.5f))
    } else {
      listOf(Color.LightGray.copy(alpha = 0.6f), Color.LightGray)
    }

  val dpValue =
    if (shimmerAnimationType != ShimmerAnimationType.FADE) {
      translateAnim.dp
    } else {
      2000.dp
    }

  @Composable
  fun buttonColors(type: ShimmerAnimationType) =
    ButtonDefaults.buttonColors(
      containerColor =
        if (shimmerAnimationType == type) MaterialTheme.colorScheme.primary else Color.LightGray
    )

  Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
    Row(
      horizontalArrangement = Arrangement.SpaceAround,
      modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
      Button(
        onClick = { shimmerAnimationType = ShimmerAnimationType.FADE },
        colors = buttonColors(ShimmerAnimationType.FADE),
        modifier = Modifier.width(200.dp).padding(8.dp)
      ) {
        Text(text = "Fading")
      }
      Button(
        onClick = { shimmerAnimationType = ShimmerAnimationType.TRANSLATE },
        colors = buttonColors(ShimmerAnimationType.TRANSLATE),
        modifier = Modifier.width(200.dp).padding(8.dp)
      ) {
        Text(text = "Translating")
      }
    }
    Row(
      horizontalArrangement = Arrangement.SpaceAround,
      modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
      Button(
        onClick = { shimmerAnimationType = ShimmerAnimationType.FADETRANSLATE },
        colors = buttonColors(ShimmerAnimationType.FADETRANSLATE),
        modifier = Modifier.width(200.dp).padding(8.dp)
      ) {
        Text(text = "Fade+Translate")
      }
      Button(
        onClick = { shimmerAnimationType = ShimmerAnimationType.VERTICAL },
        colors = buttonColors(ShimmerAnimationType.VERTICAL),
        modifier = Modifier.width(200.dp).padding(8.dp)
      ) {
        Text(text = "Vertical")
      }
    }

    ShimmerItem(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
    ShimmerItemBig(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
    ShimmerItem(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
    ShimmerItem(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
  }
}

@Composable
fun ShimmerItem(lists: List<Color>, floatAnim: Float = 0f, isVertical: Boolean) {
  val brush =
    if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim)
    else Brush.horizontalGradient(lists, 0f, floatAnim)
  Row(modifier = Modifier.padding(16.dp)) {
    Spacer(modifier = Modifier.size(100.dp).background(brush = brush))
    Column(modifier = Modifier.padding(8.dp)) {
      Spacer(
        modifier = Modifier.fillMaxWidth().height(30.dp).padding(8.dp).background(brush = brush)
      )
      Spacer(
        modifier = Modifier.fillMaxWidth().height(30.dp).padding(8.dp).background(brush = brush)
      )
      Spacer(
        modifier = Modifier.fillMaxWidth().height(30.dp).padding(8.dp).background(brush = brush)
      )
    }
  }
}

@Composable
fun ShimmerItemBig(lists: List<Color>, floatAnim: Float = 0f, isVertical: Boolean) {
  val brush =
    if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim)
    else Brush.horizontalGradient(lists, 0f, floatAnim)
  Column(modifier = Modifier.padding(16.dp)) {
    Spacer(modifier = Modifier.fillMaxWidth().size(200.dp).background(brush = brush))
    Spacer(modifier = Modifier.height(8.dp))
    Spacer(
      modifier =
        Modifier.fillMaxWidth().height(30.dp).padding(vertical = 8.dp).background(brush = brush)
    )
    Spacer(
      modifier =
        Modifier.fillMaxWidth().height(30.dp).padding(vertical = 8.dp).background(brush = brush)
    )
  }
}
