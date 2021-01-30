package com.guru.composecookbook.ui.demoapps.paint

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.templates.components.ColorPicker
import com.guru.composecookbook.ui.utils.VerticalGrid

@ExperimentalAnimationApi
@Composable
fun PaintApp() {
    MaterialTheme {
        Scaffold(topBar = { PaintAppBar() }) {
            PaintBody()
        }
    }
}

@Composable
fun PaintAppBar() {
    TopAppBar(
        title = { Text("Compose Paint") },
        actions = {
            IconButton(onClick = {}, content = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = null) })
            IconButton(onClick = {}, content = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = null) })
        }
    )
}


@ExperimentalAnimationApi
@Composable
fun PaintBody() {
    Box(modifier = Modifier.fillMaxSize()) {
        val paths =  rememberPathStateList()
        val drawColor = remember { mutableStateOf(Color.Black) }
        val drawBrush = remember { mutableStateOf(5f) }
        // on every change of brush or color start a new path and save old one in list
        paths.value.add(PathState(path = Path(), color = drawColor.value, stroke = drawBrush.value))

        DrawingCanvas(drawColor, drawBrush, paths.value)
        DrawingTools(drawColor, drawBrush)
    }
}

@ExperimentalAnimationApi
@Composable
fun DrawingCanvas(
    drawColor: MutableState<Color>,
    drawBrush: MutableState<Float>,
    paths: List<PathState>
) {
    var currentPath = paths.last().path
    val movePath = remember { mutableStateOf<Offset?>(null) }

    Canvas(modifier = Modifier.fillMaxSize().padding(top = 100.dp).pointerInteropFilter {
        when (it.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.moveTo(it.x, it.y)
            }
            MotionEvent.ACTION_MOVE -> {
                movePath.value = Offset(it.x, it.y)
            }
            else -> {
                movePath.value = null
                false
            }
        }
        true
    }) {
        movePath.value?.let {
            currentPath.lineTo(it.x, it.y)
            drawPath(
                path = currentPath,
                color = drawColor.value,
                style = Stroke(drawBrush.value)
            )
        }
        paths.forEach {
            drawPath(
                path = it.path,
                color = it.color,
                style = Stroke(it.stroke)
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun DrawingTools(drawColor: MutableState<Color>, drawBrush: MutableState<Float>) {
    var showBrushes by remember { mutableStateOf(false) }
    var showColors by remember { mutableStateOf(false) }
    val strokes = remember {  PaintDataProvider.strokeList }
    val colors = remember { PaintDataProvider.brushColors }

    Row(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            FloatingActionButton(onClick = { showBrushes = !showBrushes }) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
            }
            AnimatedVisibility(visible = showBrushes) {
                LazyColumn {
                    items(strokes) {
                        IconButton(onClick = {
                            drawBrush.value = it.toFloat()
                            showBrushes = false
                        }, modifier = Modifier
                            .padding(8.dp)
                            .border(
                                border = BorderStroke(width = with(DensityAmbient.current) { it.toDp() }, color = Color.Gray),
                                shape = CircleShape
                            )) {
                        }
                    }
                }
            }
        }
        ColorPicker(onColorSelected = { color ->
            drawColor.value = color
        })
    }
}

@Composable
fun rememberPathStateList() = remember { mutableStateOf(mutableListOf<PathState>()) }

data class PathState(
    var path: Path,
    var color: Color,
    val stroke: Float
)
