package com.guru.composecookbook.ui.home.clock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class ClockViewModel : ViewModel() {

    private val _clockState = MutableStateFlow(Triple(0f, 0f, 0f))
    val clockState: StateFlow<Triple<Float, Float, Float>> = _clockState

    init {
        initTimer()
    }

    private fun initTimer() {
        viewModelScope.launch {
            while (true) {
                val cal = Calendar.getInstance()
                val hour = cal.get(Calendar.HOUR)
                val minute = cal.get(Calendar.MINUTE)
                val second = cal.get(Calendar.SECOND)
                val secondAngle = (second * 6f).minus(90).plus(360).rem(360)
                val minuteAngle = (minute * 6f).plus(second / 10f)
                    .minus(90).plus(360).rem(360)
                val hourAngle = (hour * 30f).plus(minute / 2f)
                    .minus(90).plus(360).rem(360)
                _clockState.emit(Triple(hourAngle, minuteAngle, secondAngle))
                delay(1000L)
            }
        }
    }
}