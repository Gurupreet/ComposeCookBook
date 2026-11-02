package com.guru.composecookbook.paint

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.luminance

@Composable
fun PaintApp() {
  val paths = remember { mutableStateOf(mutableListOf<PathState>()) }
  Scaffold(topBar = { PaintAppBar { paths.value = mutableListOf() } }) { paddingValues ->
    PaintBody(paths = paths, modifier = Modifier.padding(paddingValues))
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaintAppBar(onDelete: () -> Unit) {
  val colorScheme = MaterialTheme.colorScheme
  // The old M2 default bar background was primarySurface: primary in light, and in dark the
  // elevated surface (about 0xFF181818), which surfaceContainer matches. Choose by surface
  // luminance (the darkTheme flag / in-app toggle, not the system setting).
  val light = colorScheme.surface.luminance() > 0.5f
  val container = if (light) colorScheme.primary else colorScheme.surfaceContainer
  val content = if (light) colorScheme.onPrimary else colorScheme.onSurface
  TopAppBar(
    title = { Text("Compose Paint") },
    colors =
      TopAppBarDefaults.topAppBarColors(
        containerColor = container,
        titleContentColor = content,
        navigationIconContentColor = content,
        actionIconContentColor = content,
      ),
    actions = {
      IconButton(
        onClick = onDelete,
        content = { Icon(imageVector = Icons.Default.Delete, contentDescription = null) },
      )
    },
  )
}

@Composable
fun PaintBody(paths: MutableState<MutableList<PathState>>, modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    val drawColor = remember { mutableStateOf(Color.Black) }
    val drawBrush = remember { mutableStateOf(5f) }
    val usedColors = remember { mutableStateOf(mutableSetOf(Color.Black, Color.White, Color.Gray)) }
    // on every change of brush or color start a new path and save old one in list

    paths.value.add(PathState(path = Path(), color = drawColor.value, stroke = drawBrush.value))

    DrawingCanvas(drawColor, drawBrush, usedColors, paths.value)
    DrawingTools(drawColor, drawBrush, usedColors.value)
  }
}

data class PathState(var path: Path, var color: Color, val stroke: Float)
