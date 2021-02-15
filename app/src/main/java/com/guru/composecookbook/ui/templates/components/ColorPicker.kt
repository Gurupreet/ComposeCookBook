package com.guru.composecookbook.ui.templates.components

/// Inspired from https://github.com/msasikanth/compose_colorpicker to understand hue creations .

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import android.graphics.Color as AndroidColor

@Composable
fun ColorPicker(
    onColorSelected: (Color) -> Unit
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthInPx = with(LocalDensity.current) { screenWidth.toPx() }
    var activeColor by remember { mutableStateOf<Color>(Red) }
    var dragPosition by remember { mutableStateOf(0f) }
    val dragDp = with(LocalDensity.current) { dragPosition.toDp() - 12.dp } // icon offset width

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
                        dragPosition = offset.x
                        activeColor = getActiveColor(dragPosition, screenWidthInPx)
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
                .offset(x = dragDp)
                .border(
                    border = BorderStroke(4.dp, MaterialTheme.colors.onSurface),
                    shape = CircleShape
                )
                .draggable(
                    orientation = Orientation.Horizontal,
                    onDragStarted = { dragStart ->
                        if (dragPosition == 0f) {
                            dragPosition = dragStart.x
                        }
                    },
                    onDrag = { dragDistance ->
                        dragPosition = (dragDistance + dragPosition).coerceIn(0f, screenWidthInPx)
                        activeColor = getActiveColor(dragPosition, screenWidthInPx)
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
            floatArrayOf(i.toFloat(),
                randomSaturation,
                randomLightness)
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