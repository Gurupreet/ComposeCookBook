package com.guru.composecookbook.paint

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Brush
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.colorpicker.ColorPicker
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground

@ExperimentalAnimationApi
@Composable
fun DrawingTools(
    drawColor: MutableState<Color>,
    drawBrush: MutableState<Float>,
    usedColors: MutableSet<Color>
) {
    var showBrushes by remember { mutableStateOf(false) }
    val strokes = remember { (1..50 step 5).toList() }

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
