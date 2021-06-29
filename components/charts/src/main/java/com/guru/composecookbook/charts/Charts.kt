package com.guru.composecookbook.charts

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import kotlin.math.min
import kotlin.random.Random

val pieColors = listOf(green200, purple, tiktokBlue, tiktokRed, blue700, orange200)
val increasingChart5Times = (0..10).map { it * it * 1f }.toList()
val increasingChart10Times = (10..20).map { it * it * 1f }.toList()

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
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item { Spacer(modifier = Modifier.height(30.dp)) }
            item { Text(text = "Compose charts", style = MaterialTheme.typography.h6) }
            item { Spacer(modifier = Modifier.height(10.dp)) }

            item {
                Card(modifier = Modifier.padding(16.dp), elevation = 16.dp) {
                    LineChart(
                        yAxisValues = createRandomFloatList(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        lineColors = gradientBluePurple
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(modifier = Modifier.padding(16.dp), elevation = 16.dp) {
                        LineChart(
                            yAxisValues = createRandomFloatList(),
                            modifier = Modifier
                                .width(150.dp)
                                .height(100.dp),
                            lineColors = listOf(tiktokBlue, tiktokRed)
                        )
                    }
                    Card(modifier = Modifier.padding(16.dp), elevation = 16.dp) {
                        Box {
                            LineChart(
                                yAxisValues = increasingChart5Times,
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(100.dp),
                                lineColors = listOf(tiktokBlue, blue200)
                            )
                            LineChart(
                                yAxisValues = increasingChart10Times,
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(100.dp),
                                lineColors = listOf(orange200, orange700)
                            )
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }

            item {
                Card(modifier = Modifier.padding(8.dp), elevation = 16.dp) {
                    BarCharts(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(top = 4.dp),
                        yAxisValues = createRandomFloatList(),
                        barColors = instagramGradient
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(modifier = Modifier.padding(16.dp), elevation = 16.dp) {
                        val pieChartValues: List<Float> = (0..5).map { Random.nextFloat() * 10 }
                        PieCharts(pieChartValues)
                    }
                    Card(modifier = Modifier.padding(16.dp), elevation = 16.dp) {
                        val pieChartValues: List<Float> = (0..5).map { Random.nextFloat() * 10 }
                        PieCharts(pieChartValues)
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

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
        val xBounds = Pair(0f, xTarget)
        val yBounds = getBounds(yValues)
        val scaleX = size.width / (xBounds.second - xBounds.first)
        val scaleY = size.height / (yBounds.second - yBounds.first)
        val yMove = yBounds.first * scaleY

        (0..min(yValues.size - 1, x.value.toInt())).forEach { value ->
            val xPoint = value * scaleX
            val yPoint = size.height - (yValues[value] * scaleY) + yMove
            if (value == 0) {
                path.moveTo(0f, yPoint)
                return@forEach
            }
            path.lineTo(xPoint, yPoint)
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
        val xBounds = Pair(0f, xTarget)
        val yBounds = getBounds(yValues)
        val scaleX = size.width / (xBounds.second - xBounds.first)
        val scaleY = size.height / (yBounds.second - yBounds.first)
        val yMove = yBounds.first * scaleY

        (0..min(yValues.size - 1, x.value.toInt())).forEach { value ->
            val xOffset = value * scaleX
            val yOffset = size.height - (yValues[value] * scaleY) + yMove
            drawBar(
                topLeft = Offset(xOffset, yOffset),
                width = barWidth,
                height = size.height - yOffset,
                barColors
            )
        }
    }
}

@Composable
fun PieCharts(pieValues: List<Float>, shouldAnimate: Boolean = true) {

    val index = remember { Animatable(0f) }
    val targetIndex = (pieValues.size - 1).toFloat()
    LaunchedEffect(Unit) {
        index.animateTo(
            targetValue = targetIndex,
            animationSpec = tween(
                durationMillis = if (shouldAnimate) 300 else 0,
                easing = FastOutSlowInEasing
            ),
        )
    }
    Canvas(
        modifier = Modifier
            .size(180.dp)
            .padding(16.dp)
    ) {
        val totalPieValue = pieValues.sum()
        var startAngle = 0f

        (0..min(pieValues.size - 1, index.value.toInt())).forEach { index ->
            val sliceAngle: Float = 360f * pieValues[index] / totalPieValue
            drawPieSlice(
                color = pieColors[index],
                size = size,
                startAngle = startAngle,
                sweepAngle = sliceAngle,
            )
            startAngle += sliceAngle
        }

    }
}

fun DrawScope.drawPieSlice(color: Color, size: Size, startAngle: Float, sweepAngle: Float) {
    drawArc(
        color = color,
        size = size,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = true
    )
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
        min = min.coerceAtMost(it)
        max = max.coerceAtLeast(it)
    }
    return Pair(min, max)
}

