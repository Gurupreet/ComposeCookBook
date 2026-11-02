package com.guru.composecookbook.ui.home.timer

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class TimerAnimation {
  NONE,
  SLIDE_TOP,
  SLIDE_BOTTOM,
  BOUNCE_TOP,
  BOUNCE_BOTTOM,
}

@ExperimentalAnimationApi
@Composable
fun TimerViewAnimation(
  timerData: TimerData,
  // The old default style was M2 h6. The project M2 typography does not override h6, so it was
  // the M2 library default: 20sp Medium with lineHeight 24sp and letterSpacing 0.15sp. The row
  // only uses the font size here (weight/spacing come from the explicit params and M3
  // titleLarge), so pin 20sp and reset the lineHeight to keep the row height unchanged.
  textStyle: TextStyle = TextStyle(fontSize = 20.sp, lineHeight = TextUnit.Unspecified),
  textColor: Color = Color.Black,
  fontFamily: FontFamily = FontFamily.Default,
  fontWeight: FontWeight = FontWeight.SemiBold,
  backgroundColor: Color = Color.White,
  backgroundPadding: Dp = 0.dp,
  backgroundElevation: Dp = 4.dp,
  backgroundShape: Shape = RoundedCornerShape(16.dp),
  timeAnimation: TimerAnimation = TimerAnimation.SLIDE_TOP,
  timeDurationSlide: Int = 500,
  timeSpace: String = " : ",
  icon: Painter? = null,
  iconColor: ColorFilter? = null,
) {
  /**
   * M2 Card(backgroundColor, shape, elevation) on a hardcoded color -> M3 Surface with
   * shadowElevation: the hardcoded color keeps its exact shade (a tonal CardElevation would tint
   * it) and the shadow is preserved.
   */
  Surface(
    color = backgroundColor,
    shape = backgroundShape,
    shadowElevation = backgroundElevation,
  ) {
    /** Row (alpha = 0) */
    Row(
      modifier =
        Modifier.padding(start = 8.dp, end = 8.dp).padding(all = backgroundPadding).alpha(0f),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
    ) {
      icon?.let {
        Image(
          modifier = Modifier.padding(end = 4.dp),
          painter = it,
          colorFilter = iconColor ?: ColorFilter.tint(textColor),
          contentDescription = null,
        )
      }

      Text(
        text = "00${timeSpace}00${timeSpace}00",
        style = textStyle,
        textAlign = TextAlign.Center,
        color = textColor,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
      )
    }

    Row(
      modifier = Modifier.padding(start = 8.dp, end = 8.dp).padding(all = backgroundPadding),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
    ) {
      icon?.let {
        Image(
          modifier = Modifier.padding(end = 4.dp),
          painter = it,
          colorFilter = iconColor ?: ColorFilter.tint(textColor),
          contentDescription = null,
        )
      }

      TimeViewAnimationContent(
        timeContent = timerData.hourTens,
        textStyle = textStyle,
        timerAnimation = timeAnimation,
        timeDurationSlide = timeDurationSlide,
        textColor = textColor,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
      )

      TimeViewAnimationContent(
        timeContent = timerData.hourZero,
        textStyle = textStyle,
        timerAnimation = timeAnimation,
        timeDurationSlide = timeDurationSlide,
        textColor = textColor,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
      )

      TimeViewSeparator(
        timeSpace = timeSpace,
        textStyle = textStyle,
        textColor = textColor,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
      )

      TimeViewAnimationContent(
        timeContent = timerData.minuteTens,
        textStyle = textStyle,
        timerAnimation = timeAnimation,
        timeDurationSlide = timeDurationSlide,
        textColor = textColor,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
      )

      TimeViewAnimationContent(
        timeContent = timerData.minuteZero,
        textStyle = textStyle,
        timerAnimation = timeAnimation,
        timeDurationSlide = timeDurationSlide,
        textColor = textColor,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
      )

      TimeViewSeparator(
        timeSpace = timeSpace,
        textStyle = textStyle,
        textColor = textColor,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
      )

      TimeViewAnimationContent(
        timeContent = timerData.secondTens,
        textStyle = textStyle,
        timerAnimation = timeAnimation,
        timeDurationSlide = timeDurationSlide,
        textColor = textColor,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
      )

      TimeViewAnimationContent(
        timeContent = timerData.secondZero,
        textStyle = textStyle,
        timerAnimation = timeAnimation,
        timeDurationSlide = timeDurationSlide,
        textColor = textColor,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
      )
    }
  }
}

@ExperimentalAnimationApi
private fun addAnimation(timeDurationSlide: Int, timeAnimation: TimerAnimation): ContentTransform {
  val duration =
    when (timeAnimation) {
      TimerAnimation.NONE -> 0
      else -> timeDurationSlide
    }

  return slideInVertically(animationSpec = tween(durationMillis = duration)) { height ->
    when (timeAnimation) {
      TimerAnimation.NONE -> 0
      TimerAnimation.SLIDE_TOP -> -height
      TimerAnimation.SLIDE_BOTTOM -> height
      TimerAnimation.BOUNCE_TOP -> -height
      TimerAnimation.BOUNCE_BOTTOM -> height
    }
  } + fadeIn(animationSpec = tween(durationMillis = duration)) with
    slideOutVertically(animationSpec = tween(durationMillis = duration)) { height ->
      when (timeAnimation) {
        TimerAnimation.NONE -> 0
        TimerAnimation.SLIDE_TOP -> height
        TimerAnimation.SLIDE_BOTTOM -> -height
        TimerAnimation.BOUNCE_TOP -> -height
        TimerAnimation.BOUNCE_BOTTOM -> height
      }
    } + fadeOut(animationSpec = tween(durationMillis = duration))
}

@ExperimentalAnimationApi
@Composable
fun TimeViewAnimationContent(
  timeContent: String,
  textStyle: TextStyle,
  textColor: Color,
  timerAnimation: TimerAnimation,
  timeDurationSlide: Int,
  fontFamily: FontFamily,
  fontWeight: FontWeight,
) {
  AnimatedContent(
    targetState = timeContent,
    transitionSpec = {
      addAnimation(timeDurationSlide = timeDurationSlide, timeAnimation = timerAnimation)
        .using(SizeTransform(clip = true))
    },
  ) { targetCount ->
    Text(
      text = targetCount,
      style = textStyle,
      textAlign = TextAlign.Center,
      color = textColor,
      fontFamily = fontFamily,
      fontWeight = fontWeight,
    )
  }
}

@ExperimentalAnimationApi
@Composable
fun TimeViewSeparator(
  timeSpace: String,
  textStyle: TextStyle,
  textColor: Color,
  fontFamily: FontFamily,
  fontWeight: FontWeight,
) {
  Text(
    text = timeSpace,
    style = textStyle,
    textAlign = TextAlign.Center,
    color = textColor,
    fontFamily = fontFamily,
    fontWeight = fontWeight,
  )
}
