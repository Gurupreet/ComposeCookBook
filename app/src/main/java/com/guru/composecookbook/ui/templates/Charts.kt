package com.guru.composecookbook.ui.templates

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
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
import com.guru.composecookbook.theme.*
import kotlin.random.Random

val lineChartX = listOf<Float>(100f, 200f, 300f, 400f, 500f, 600f, 900f, 1000f, 1100f, 1200f,
    1300f, 1400f, 1500f)
val lineChartY = listOf<Float>(100f, 200f, 250f, 110f, 500f, 200f, 900f, 200f, 250f, 110f, 500f,
    200f, 600f)

fun createRandomFloatList(): List<Float> {
    val list = mutableListOf<Float>()
    (0..20).forEach { _ ->
        list.add(Random.nextFloat()*10)
    }
    return list
}

@Composable
fun Charts() {
    Scaffold {
        ScrollableColumn(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Compose charts", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(30.dp))
            LineChart()
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun LineChart() {
    val x = remember { Animatable(0f) }
    val yValues = remember { createRandomFloatList() }
    LaunchedEffect(Unit) {
        x.animateTo(
            targetValue = 20f,
            animationSpec = tween(durationMillis = 500, easing = LinearEasing),
        )
    }

    Canvas(modifier = Modifier.fillMaxWidth().height(300.dp).padding(8.dp)) {
        val path = Path()
        val xbounds = Pair(0f, 20f)
        val ybounds = getBounds(yValues)
        val scaleX = size.width / (xbounds.second - xbounds.first)
        val scaleY = size.height / (ybounds.second - ybounds.first)
        val yMove = ybounds.first * scaleY
        val xMove = xbounds.first * scaleX

        val startX = 0f * scaleX + xMove
        val startY = yValues[0] * scaleY + yMove
        path.moveTo(0f, startY)

        (1..x.value.toInt()).forEachIndexed { index, value ->
            val x = value * scaleX + xMove - startX
            val y = size.height - (yValues[index] * scaleY) + yMove
            path.lineTo(x, y)
        }

        drawRect(color = Color.LightGray, size = size)
        drawPath(
            path = path,
            brush = Brush.linearGradient(listOf(purple200, purple, purple700)),
            style = Stroke(width = 6f)
        )
    }
    Spacer(modifier = Modifier.height(24.dp))

    val yValues2 = remember { createRandomFloatList() }
    val yValues3 = remember { createRandomFloatList() }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Canvas(modifier = Modifier.width(200.dp).height(100.dp).padding(4.dp)) {
            val path = Path()
            val xbounds = Pair(0f, 20f)
            val ybounds = getBounds(yValues2)
            val scaleX = size.width / (xbounds.second - xbounds.first)
            val scaleY = size.height / (ybounds.second - ybounds.first)
            val yMove = ybounds.first * scaleY
            val xMove = xbounds.first * scaleX

            val startX = 0f * scaleX + xMove
            val startY = yValues2[0] * scaleY + yMove
            path.moveTo(0f, startY)

            (1..x.value.toInt()).forEachIndexed { index, value ->
                val x = value * scaleX + xMove - startX
                val y = size.height - (yValues2[index] * scaleY) + yMove
                path.lineTo(x, y)
            }

            drawRect(color = Color.LightGray, size = size)
            drawPath(
                path = path,
                brush = Brush.linearGradient(listOf(tiktokBlue, tiktokRed)),
                style = Stroke(width = 6f)
            )
        }
        Canvas(modifier = Modifier.width(200.dp).height(100.dp).padding(4.dp)) {
            val path = Path()
            val xbounds = Pair(0f, 20f)
            val ybounds = getBounds(yValues3)
            val scaleX = size.width / (xbounds.second - xbounds.first)
            val scaleY = size.height / (ybounds.second - ybounds.first)
            val yMove = ybounds.first * scaleY
            val xMove = xbounds.first * scaleX

            val startX = 0f * scaleX + xMove
            val startY = yValues3[0] * scaleY + yMove
            path.moveTo(0f, startY)

            (1..x.value.toInt()).forEachIndexed { index, value ->
                val x = value * scaleX + xMove - startX
                val y = size.height - (yValues3[index] * scaleY) + yMove
                path.lineTo(x, y)
            }

            drawRect(color = Color.LightGray, size = Size(size.width, size.height))
            drawPath(
                path = path,
                brush = Brush.linearGradient(listOf(orange200, orange500, orange700)),
                style = Stroke(width = 6f)
            )
        }
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

