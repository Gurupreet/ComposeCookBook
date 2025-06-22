package com.guru.composecookbook.ui.home.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.Calendar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

  private val todayMax =
    Calendar.getInstance()
      .apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
      }
      .timeInMillis

  private val _timerState =
    MutableStateFlow(TimerData(todayMax.minus(Calendar.getInstance().timeInMillis)))
  val timerState: StateFlow<TimerData> = _timerState

  init {
    initTimer()
  }

  private fun initTimer() {
    viewModelScope.launch {
      while (true) {
        val next = timerState.value.millis.minus(1000)
        _timerState.emit(timerState.value.copy(millis = next))
        delay(1000L)
      }
    }
  }
}
