package com.guru.composecookbook.ui.demoapps.paint

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.ui.templates.components.ColorPicker
import com.guru.composecookbook.ui.utils.horizontalGradientBackground

@ExperimentalAnimationApi
@Composable
fun PaintApp() {
    ComposeCookBookTheme(darkTheme = false) {
        val paths = rememberPathStateList()
        Scaffold(
            topBar = {
                PaintAppBar { paths.value = mutableListOf() }
            }
        ) {
            PaintBody(paths)
        }
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

@ExperimentalAnimationApi
@Composable
fun DrawingCanvas(
    drawColor: MutableState<Color>,
    drawBrush: MutableState<Float>,
    usedColors: MutableState<MutableSet<Color>>,
    paths: List<PathState>
) {
    val currentPath = paths.last().path
    val movePath = remember { mutableStateOf<Offset?>(null) }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp)
        .pointerInteropFilter {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    currentPath.moveTo(it.x, it.y)
                    usedColors.value.add(drawColor.value)
                }
                MotionEvent.ACTION_MOVE -> {
                    movePath.value = Offset(it.x, it.y)
                }
                else -> {
                    movePath.value = null
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
fun DrawingTools(
    drawColor: MutableState<Color>,
    drawBrush: MutableState<Float>,
    usedColors: MutableSet<Color>
) {
    var showBrushes by remember { mutableStateOf(false) }
    val strokes = remember { PaintDataProvider.strokeList }

    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        ColorPicker(
            onColorSelected = { color ->
                drawColor.value = color
            })
        Row(
            modifier = Modifier
                .horizontalGradientBackground(listOf(graySurface, Color.Black))
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .horizontalScroll(rememberScrollState())
                .animateContentSize()
        ) {
            usedColors.forEach {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = null,
                    tint = it,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            drawColor.value = it
                        }
                )
            }
        }
        FloatingActionButton(
            onClick = { showBrushes = !showBrushes },
            modifier = Modifier.padding(vertical = 4.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Brush,
                contentDescription = null,
                tint = drawColor.value
            )
        }
        AnimatedVisibility(visible = showBrushes) {
            LazyColumn {
                items(strokes) {
                    IconButton(
                        onClick = {
                            drawBrush.value = it.toFloat()
                            showBrushes = false
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .border(
                                border = BorderStroke(
                                    width = with(LocalDensity.current) { it.toDp() },
                                    color = Color.Gray
                                ),
                                shape = CircleShape
                            )
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun rememberPathStateList() = remember { mutableStateOf(mutableListOf<PathState>()) }

@Preview
@Composable
fun DrawingToolsPreview() {
    Text(text = "Drawing")
}

data class PathState(
    var path: Path,
    var color: Color,
    val stroke: Float
)
