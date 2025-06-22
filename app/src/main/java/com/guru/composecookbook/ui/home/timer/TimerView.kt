package com.guru.composecookbook.ui.home.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.blue200
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.orange700
import com.guru.composecookbook.theme.teal200
import com.guru.composecookbook.theme.typography
import java.util.concurrent.TimeUnit

enum class TimerType(private val type: Int) {
  HH_MM_SS(0x00000111),
  HH_MM(0x00000110),
  MM_SS(0x00000011);

  private val hour = 0x00000100
  private val minute = 0x00000010
  private val second = 0x00000001

  fun hourEnabled() = type.and(hour) == hour

  fun minuteEnabled() = type.and(minute) == minute

  fun secondEnabled() = type.and(second) == second
}

data class TimerData(val millis: Long = 0) {
  val hourTens: String
  val hourZero: String
  val minuteTens: String
  val minuteZero: String
  val secondTens: String
  val secondZero: String

  init {
    var millisRemains = millis
    val hours = TimeUnit.MILLISECONDS.toHours(millisRemains)
    hourTens = hours.div(10).coerceIn(0L..9L).toString()
    hourZero = hours.rem(10).toString()
    millisRemains -= TimeUnit.HOURS.toMillis(hours)

    val minute = TimeUnit.MILLISECONDS.toMinutes(millisRemains)
    minuteTens = minute.div(10).toString()
    minuteZero = minute.rem(10).toString()
    millisRemains -= TimeUnit.MINUTES.toMillis(minute)

    val seconds = TimeUnit.MILLISECONDS.toSeconds(millisRemains)
    secondTens = seconds.div(10).toString()
    secondZero = seconds.rem(10).toString()
  }
}

@Composable
fun TimerView(
  type: TimerType,
  timerData: TimerData,
  modifier: Modifier = Modifier,
  componentModifier: Modifier = Modifier,
  digitModifier: Modifier = Modifier,
  digitStyle: TextStyle = LocalTextStyle.current,
  separator: String = ":",
  separatorModifier: Modifier = Modifier,
  separatorStyle: TextStyle = LocalTextStyle.current,
) {
  Row(modifier = modifier) {
    if (type.hourEnabled()) {
      Row(modifier = componentModifier) {
        Text(text = timerData.hourTens, modifier = digitModifier, style = digitStyle)
        Text(text = timerData.hourZero, modifier = digitModifier, style = digitStyle)
      }
    }
    if (type.hourEnabled() && type.minuteEnabled()) {
      Text(text = separator, modifier = separatorModifier, style = separatorStyle)
    }
    if (type.minuteEnabled()) {
      Row(modifier = componentModifier) {
        Text(text = timerData.minuteTens, modifier = digitModifier, style = digitStyle)
        Text(text = timerData.minuteZero, modifier = digitModifier, style = digitStyle)
      }
    }
    if (type.minuteEnabled() && type.secondEnabled()) {
      Text(text = separator, modifier = separatorModifier, style = separatorStyle)
    }
    if (type.secondEnabled()) {
      Row(modifier = componentModifier) {
        Text(text = timerData.secondTens, modifier = digitModifier, style = digitStyle)
        Text(text = timerData.secondZero, modifier = digitModifier, style = digitStyle)
      }
    }
  }
}

@Preview
@Composable
fun TimerViewPreview() {
  TimerView(
    type = TimerType.HH_MM_SS,
    timerData = TimerData(0),
    modifier =
      Modifier.border(width = 1.dp, color = green700, shape = RoundedCornerShape(4.dp))
        .padding(horizontal = 8.dp, vertical = 4.dp),
    componentModifier =
      Modifier.background(color = blue200, shape = RoundedCornerShape(4.dp))
        .padding(horizontal = 4.dp, vertical = 4.dp),
    digitStyle = typography.body1.copy(color = orange700),
    separatorModifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
    separatorStyle = typography.body1.copy(color = teal200)
  )
}
