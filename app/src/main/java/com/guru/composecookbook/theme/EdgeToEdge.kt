package com.guru.composecookbook.theme

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge

// androidx.activity's own defaults are internal; they keep nav buttons legible below API 29.
private val LightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val DarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

/** Edge-to-edge with system bar icons following the in-app theme instead of the OS theme. */
fun ComponentActivity.enableEdgeToEdge(darkTheme: Boolean) {
  enableEdgeToEdge(
    statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { darkTheme },
    navigationBarStyle = SystemBarStyle.auto(LightScrim, DarkScrim) { darkTheme },
  )
}
