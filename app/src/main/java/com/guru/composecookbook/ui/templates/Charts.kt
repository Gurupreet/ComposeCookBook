package com.guru.composecookbook.ui.templates

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

val pieChartValues = listOf(100f, 200f, 300f, 400f, 260f)
val pieColors = listOf(green200, purple, tiktokRed, tiktokBlue, green700, orange700)

fun createRandomFloatList(): List<Float> {
    val list = mutableListOf<Float>()
    (0..20).forEach { _ ->
        list.add(Random.nextFloat() * 10)
    }
    return list
}

@Composable
fun Charts() {
    Scaffold {
        ScrollableColumn(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Compose charts", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier.padding(16.dp), elevation = 16.dp) {
                LineChart(
                    yAxisValues = createRandomFloatList(),
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    lineColors = gradientBluePurple
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Card(modifier = Modifier.padding(16.dp), elevation = 16.dp) {
                    LineChart(
                        yAxisValues = createRandomFloatList(),
                        modifier = Modifier.width(150.dp).height(80.dp),
                        lineColors = listOf(tiktokBlue, tiktokRed)
                    )
                }
                Card(modifier = Modifier.padding(16.dp), elevation = 16.dp) {
                    LineChart(
                        yAxisValues = createRandomFloatList(),
                        modifier = Modifier.width(150.dp).height(80.dp),
                        lineColors = instagramGradient
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(modifier = Modifier.padding(8.dp), elevation = 16.dp) {
                BarCharts(
                    modifier = Modifier.fillMaxWidth().height(150.dp).padding(top = 4.dp),
                    yAxisValues = createRandomFloatList(),
                    barColors = instagramGradient
                )
            }

            PieCharts(pieChartValues)
        }
    }
}

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    lineColors: List<Color> = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primary),
    lineWidth: Float = 4f,
    yAxisValues: List<Float>,
    shouldAnimate: Boolean = true,
) {
    val yValues = remember { yAxisValues }
    val x = remember { Animatable(0f) }
    val xTarget = (yValues.size - 1).toFloat()

    LaunchedEffect(Unit) {
        x.animateTo(
            targetValue = xTarget,
            animationSpec = tween(
                durationMillis = if (shouldAnimate) 500 else 0,
                easing = LinearEasing
            ),
        )
    }

    Canvas(modifier = modifier.padding(8.dp)) {
        val path = Path()
        val xbounds = Pair(0f, xTarget)
        val ybounds = getBounds(yValues)
        val scaleX = size.width / (xbounds.second - xbounds.first)
        val scaleY = size.height / (ybounds.second - ybounds.first)
        val yMove = ybounds.first * scaleY

        (0..x.value.toInt()).forEach { value ->
            val x = value * scaleX
            val y = size.height - (yValues[value] * scaleY) + yMove
            if (value == 0) {
                path.moveTo(0f, y)
                return@forEach
            }
            path.lineTo(x, y)
        }

        drawPath(
            path = path,
            brush = Brush.linearGradient(lineColors),
            style = Stroke(width = lineWidth)
        )
    }
}

@Composable
fun BarCharts(
    modifier: Modifier = Modifier,
    barColors: List<Color> = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primary),
    barWidth: Float = 20f,
    yAxisValues: List<Float>,
    shouldAnimate: Boolean = true
) {
    val x = remember { Animatable(0f) }
    val yValues = remember { yAxisValues }
    val xTarget = (yValues.size - 1).toFloat()
    LaunchedEffect(Unit) {
        x.animateTo(
            targetValue = xTarget,
            animationSpec = tween(
                durationMillis = if (shouldAnimate) 500 else 0,
                easing = LinearEasing
            ),
        )
    }

    Canvas(modifier = modifier.padding(horizontal = 8.dp)) {
        val xbounds = Pair(0f, xTarget)
        val ybounds = getBounds(yValues)
        val scaleX = size.width / (xbounds.second - xbounds.first)
        val scaleY = size.height / (ybounds.second - ybounds.first)
        val yMove = ybounds.first * scaleY

        (0..x.value.toInt()).forEach { value ->
            val x = value * scaleX
            val y = size.height - (yValues[value] * scaleY) + yMove
            drawBar(topLeft = Offset(x, y), width = barWidth, height = size.height - y, barColors)
        }
    }
}

@Composable
fun PieCharts(pieValues: List<Float>, shouldAnimate: Boolean = true) {

    val index = remember { Animatable(0f) }
    val pieValues = remember { pieValues }
    val targetIndex = (pieValues.size - 1).toFloat()
    LaunchedEffect(Unit) {
        index.animateTo(
            targetValue = targetIndex,
            animationSpec = tween(
                durationMillis = if (shouldAnimate) 500 else 0,
                easing = LinearEasing
            ),
        )
    }
    Canvas(modifier = Modifier.size(200.dp).padding(16.dp)) {
        var totalPieValue = pieChartValues.sum()
        var startAngle = 0f
        var radius = size / 2f
        (0..index.value.toInt()).forEach { index  ->
            var sliceAngle = 360f * pieChartValues[index] / totalPieValue
            drawPieSlice(
                color = pieColors[index],
                size = size,
                startAngle = startAngle,
                sweepAngle = sliceAngle
            )
            var textX = radius * cos(startAngle + sliceAngle / 2)
            var textY = radius * sin(startAngle + sliceAngle / 2)
            startAngle += sliceAngle
            //drawLine()
        }

    }
}

fun DrawScope.drawPieSlice(color: Color, size: Size, startAngle: Float, sweepAngle: Float) {
    drawArc(color = color, size = size, startAngle = startAngle, sweepAngle = sweepAngle,
        useCenter = true)
}

fun DrawScope.drawBar(topLeft: Offset, width: Float, height: Float, colors: List<Color>) {
    drawRect(
        topLeft = topLeft,
        brush = Brush.linearGradient(colors),
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

