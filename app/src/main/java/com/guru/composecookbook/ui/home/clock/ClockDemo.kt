package com.guru.composecookbook.ui.home.clock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClockDemo(viewModel: ClockViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
  val clockState: Triple<Float, Float, Float> by viewModel.clockState.collectAsState()
  Surface {
    Column(
      modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(16.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      ClockView(
        hourAngle = clockState.first,
        minuteAngle = clockState.second,
        secondAngle = clockState.third,
        modifier =
          Modifier.height(200.dp)
            .width(200.dp)
            .background(color = MaterialTheme.colors.surface, shape = CircleShape)
      )
    }
  }
}
