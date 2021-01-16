package com.guru.composecookbook.ui.templates

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.theme.purple200
import com.guru.composecookbook.theme.purple700

val lineChartX = listOf<Float>(100f, 200f, 300f, 400f, 500f, 600f, 900f, 1000f, 1100f, 1200f,
    1300f, 1400f, 1500f)
val lineChartY = listOf<Float>(100f, 200f, 250f, 110f, 500f, 200f, 900f, 200f, 250f, 110f, 500f,
    200f, 600f)

@Composable
fun Charts() {
    Scaffold {
        ScrollableColumn(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Line charts", style = MaterialTheme.typography.h6)
            var xValues = remember { mutableStateOf<List<Float>>(emptyList()) }
            var yValues = remember { mutableStateOf<List<Float>>(emptyList()) }
            onActive {
                xValues.value = lineChartX
                yValues.value = lineChartY
            }
            LineChart(xValues.value, yValues.value)
            Spacer(modifier = Modifier.height(20.dp))
            BarCharts(xValues = xValues.value, yValues = yValues.value)
        }
    }
}

@Composable
fun LineChart(xValues: List<Float>, yValues: List<Float>) {
    Canvas(modifier = Modifier.fillMaxWidth().height(300.dp)) {
        val path = Path()
        if (xValues.isEmpty() || yValues.isEmpty()) return@Canvas
        val xbounds = getBounds(xValues)
        val ybounds = getBounds(yValues)
        val scaleX = size.width / (xbounds.second - xbounds.first)
        val scaleY = size.height / (ybounds.second - ybounds.first)
        val yMove = xbounds.first * scaleY
        val xMove = xbounds.first * scaleX

        val startX = xValues[0] * scaleX + xMove
        val startY = yValues[0] * scaleY + yMove
        path.moveTo(0f, startY)

        (1 until xValues.size).forEach { index ->
            val x = xValues[index] * scaleX + xMove - startX
            val y = size.height - (yValues[index] * scaleY) + yMove
            path.lineTo(x, y)
        }

        drawRect(color = Color.Gray, size = size)
        drawPath(
            path = path,
            brush = Brush.linearGradient(listOf(purple200, purple, purple700)),
            style = Stroke(width = 6f)
        )
    }
}

@Composable
fun BarCharts(xValues: List<Float>, yValues: List<Float>) {
//    Canvas(modifier = Modifier.fillMaxWidth().height(300.dp)) {
//        if (xValues.isEmpty() || yValues.isEmpty()) return@Canvas
//        drawRect(color = Color.Gray, size = size)
//        val xbounds = getBounds(xValues)
//        val ybounds = getBounds(yValues)
//        val scaleX = size.width/(xbounds.second - xbounds.first)
//        val scaleY = size.height/(ybounds.second - ybounds.first)
//        val yMove = xbounds.first*scaleY
//        val xMove = xbounds.first*scaleX
//        val startX = xValues[0] * scaleX + xMove
//        val startY = yValues[0] * scaleY + yMove
//        (1 until xValues.size).forEach { index ->
//            val x = xValues[index] * scaleX + xMove - startX
//            val y = size.height-(yValues[index] * scaleY) + yMove
//            drawBar(topLeft = Offset(x, size.height), width = xMove, height = y)
//        }
//
//    }
}


fun DrawScope.drawBar(topLeft: Offset, width: Float, height: Float) {
    drawRect(
        topLeft = topLeft,
        brush = Brush.linearGradient(listOf(purple200, purple, purple700)),
        size = Size(width, height)
    )
}

fun getBounds(list: List<Float>): Pair<Float, Float> {
    var min = Float.MAX_VALUE
    var max = -Float.MAX_VALUE
    list.forEach {
        min = Math.min(min, it)
        max = Math.max(max, it)
    }
    return Pair(min, max)
}

