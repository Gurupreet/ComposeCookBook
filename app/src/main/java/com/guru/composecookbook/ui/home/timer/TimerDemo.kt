package com.guru.composecookbook.ui.home.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guru.composecookbook.theme.typography

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
                            color = MaterialTheme.colors.onSurface,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    componentModifier = Modifier
                        .background(
                            color = MaterialTheme.colors.onSurface,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 4.dp, vertical = 4.dp),
                    digitStyle = typography.body1.copy(color = MaterialTheme.colors.onPrimary),
                    separatorModifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                    separatorStyle = typography.body1.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}