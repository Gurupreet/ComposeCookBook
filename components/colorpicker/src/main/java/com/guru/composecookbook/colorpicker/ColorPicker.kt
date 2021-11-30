package com.guru.composecookbook.colorpicker

/// Inspired from https://github.com/msasikanth/compose_colorpicker to understand hue creations .

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography
import kotlin.math.roundToInt
import kotlin.random.Random
import android.graphics.Color as AndroidColor

@Composable
fun ColorPicker(
    onColorSelected: (Color) -> Unit
) {
    Text(
        text = "Color picker with draggable",
        style = typography.subtitle2,
        modifier = Modifier.padding(8.dp)
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthInPx = with(LocalDensity.current) { screenWidth.toPx() }
    var activeColor by remember { mutableStateOf(Red) }

    val max = screenWidth - 16.dp
    val min = 0.dp
    val (minPx, maxPx) = with(LocalDensity.current) { min.toPx() to max.toPx() }
    val dragOffset = remember { mutableStateOf(0f) }
    Box(modifier = Modifier.padding(8.dp)) {
        //slider
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(brush = colorMapGradient(screenWidthInPx))
                .align(Alignment.Center)
                .pointerInput("painter") {
                    detectTapGestures { offset ->
                        dragOffset.value = offset.x
                        activeColor = getActiveColor(dragOffset.value, screenWidthInPx)
                        onColorSelected.invoke(activeColor)
                    }
                }
        )
        // draggable icon
        Icon(
            imageVector = Icons.Filled.FiberManualRecord,
            tint = activeColor,
            contentDescription = null,
            modifier = Modifier
                .offset { IntOffset(dragOffset.value.roundToInt(), 0) }
                .border(
                    border = BorderStroke(4.dp, MaterialTheme.colorScheme.onSurface),
                    shape = CircleShape
                )
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        val newValue = dragOffset.value + delta
                        dragOffset.value = newValue.coerceIn(minPx, maxPx)
                        activeColor = getActiveColor(dragOffset.value, screenWidthInPx)
                        onColorSelected.invoke(activeColor)
                    }
                )
        )
    }
}

fun createColorMap(): List<Color> {
    val colorList = mutableListOf<Color>()
    for (i in 0..360 step (2)) {
        val randomSaturation = 90 + Random.nextFloat() * 10
        val randomLightness = 50 + Random.nextFloat() * 10
        val hsv = AndroidColor.HSVToColor(
            floatArrayOf(
                i.toFloat(),
                randomSaturation,
                randomLightness
            )
        )
        colorList.add(Color(hsv))
    }

    return colorList
}

fun colorMapGradient(screenWidthInPx: Float) = Brush.horizontalGradient(
    colors = createColorMap(),
    startX = 0f,
    endX = screenWidthInPx
)

fun getActiveColor(dragPosition: Float, screenWidth: Float): Color {
    val hue = (dragPosition / screenWidth) * 360f
    val randomSaturation = 90 + Random.nextFloat() * 10
    val randomLightness = 50 + Random.nextFloat() * 10
    return Color(
        AndroidColor.HSVToColor(
            floatArrayOf(
                hue,
                randomSaturation,
                randomLightness
            )
        )
    )
}