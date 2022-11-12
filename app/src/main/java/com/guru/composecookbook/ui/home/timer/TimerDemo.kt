package com.guru.composecookbook.ui.home.timer

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.R

@ExperimentalAnimationApi
@Composable
fun TimerDemo(
    timerViewModel: TimerViewModel = viewModel()
) {
    val timerData: TimerData by timerViewModel.timerState.collectAsState()
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimerType.values().forEach {
                TimerView(
                    type = it,
                    timerData = timerData,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    componentModifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 4.dp, vertical = 4.dp),
                    digitStyle = typography.body1.copy(color = MaterialTheme.colorScheme.onPrimary),
                    separatorModifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                    separatorStyle = typography.body1.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            TimerViewAnimation(
                timerData = timerData,
                timeAnimation = TimerAnimation.SLIDE_BOTTOM,
                backgroundColor = Color.Black,
                textColor = Color.White,
                icon = painterResource(id = R.drawable.ic_baseline_access_time_24),
                iconColor = ColorFilter.tint(color = Color.White)
            )
            Spacer(modifier = Modifier.height(12.dp))

            TimerViewAnimation(
                timerData = timerData,
                timeAnimation = TimerAnimation.NONE,
                backgroundColor = Color.Black,
                textColor = Color.White,
                icon = painterResource(id = R.drawable.ic_baseline_access_time_24),
                iconColor = ColorFilter.tint(color = Color.White)
            )
            Spacer(modifier = Modifier.height(12.dp))

            TimerViewAnimation(
                timerData = timerData,
                timeAnimation = TimerAnimation.SLIDE_TOP,
                backgroundColor = Color.Black,
                textColor = Color.White,
                icon = painterResource(id = R.drawable.ic_baseline_access_time_24),
                iconColor = ColorFilter.tint(color = Color.White)
            )
            Spacer(modifier = Modifier.height(12.dp))

            TimerViewAnimation(
                timerData = timerData,
                timeAnimation = TimerAnimation.BOUNCE_BOTTOM,
                backgroundColor = Color.Black,
                textColor = Color.White,
                icon = painterResource(id = R.drawable.ic_baseline_access_time_24),
                iconColor = ColorFilter.tint(color = Color.White)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}