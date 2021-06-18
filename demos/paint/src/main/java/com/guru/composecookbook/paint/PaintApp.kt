package com.guru.composecookbook.paint

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun PaintApp() {
    val paths = remember { mutableStateOf(mutableListOf<PathState>()) }
    Scaffold(
        topBar = {
            PaintAppBar { paths.value = mutableListOf() }
        }
    ) {
        PaintBody(paths)
    }
}

@Composable
fun PaintAppBar(onDelete: () -> Unit) {
    TopAppBar(
        title = { Text("Compose Paint") },
        actions = {
            IconButton(onClick = onDelete, content = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            })
        }
    )
}


@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun PaintBody(paths: MutableState<MutableList<PathState>>) {
    Box(modifier = Modifier.fillMaxSize()) {
        val drawColor = remember { mutableStateOf(Color.Black) }
        val drawBrush = remember { mutableStateOf(5f) }
        val usedColors =
            remember { mutableStateOf(mutableSetOf(Color.Black, Color.White, Color.Gray)) }
        // on every change of brush or color start a new path and save old one in list

        paths.value.add(PathState(path = Path(), color = drawColor.value, stroke = drawBrush.value))

        DrawingCanvas(drawColor, drawBrush, usedColors, paths.value)
        DrawingTools(drawColor, drawBrush, usedColors.value)
    }
}

data class PathState(
    var path: Path,
    var color: Color,
    val stroke: Float
)
