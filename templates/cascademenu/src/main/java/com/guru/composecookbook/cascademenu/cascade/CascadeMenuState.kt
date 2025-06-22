package com.guru.composecookbook.cascademenu.cascade

import androidx.compose.runtime.*

class CascadeMenuState<T : Any>(currentMenuItem: CascadeMenuItem<T>) {
  private var _currentMenu by mutableStateOf(currentMenuItem)

  var currentMenuItem: CascadeMenuItem<T>
    get() = _currentMenu
    set(value) {
      _currentMenu = value
    }
}
