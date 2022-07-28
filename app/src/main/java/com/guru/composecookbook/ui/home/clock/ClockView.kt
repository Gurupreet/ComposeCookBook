package com.guru.composecookbook.ui.home.clock

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ClockView(
    hourAngle: Float,
    minuteAngle: Float,
    secondAngle: Float,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
    hourHandColor: Color = MaterialTheme.colors.onSurface,
    minuteHandColor: Color = MaterialTheme.colors.onSurface,
    secondHandColor: Color = MaterialTheme.colors.onSurface
) {
    Canvas(modifier = modifier) {
        // Outer Circle
        drawCircle(color = textColor, style = Stroke(2.dp.toPx()))

        // Hour Lines
        val lineOuterR = (size.minDimension / 2.0f).minus(6.dp.toPx())
        val lineInnerR = (size.minDimension / 2.0f).minus(18.dp.toPx())
        val origin = size.minDimension / 2.0f
        for (i in 0..11) {
            val xRad = cos(30.0 * i * Math.PI / 180F)
            val yRad = sin(30.0 * i * Math.PI / 180F)
            drawLine(
                color = textColor,
                start = Offset(
                    (origin + (lineInnerR * xRad)).toFloat(),
                    (origin + (lineInnerR * yRad)).toFloat()
                ),
                end = Offset(
                    (origin + (lineOuterR * xRad)).toFloat(),
                    (origin + (lineOuterR * yRad)).toFloat()
                ),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round,
            )
        }

        // Inner Circle
        val rInnerCircle = (size.minDimension / 2.0f).minus(22.dp.toPx())
        drawCircle(color = textColor, radius = rInnerCircle, style = Stroke(1.dp.toPx()))

        // Center
        drawCircle(color = textColor, radius = 4.dp.toPx())

        // Hour Hand
        val xRadHour = cos(hourAngle * Math.PI / 180F)
        val yRadHour = sin(hourAngle * Math.PI / 180F)
        val rHour = rInnerCircle.times(3).div(4)
        drawLine(
            color = hourHandColor,
            start = Offset(origin, origin),
            end = Offset(
                (origin + (rHour * xRadHour)).toFloat(),
                (origin + (rHour * yRadHour)).toFloat()
            ),
            strokeWidth = 3.dp.toPx(),
            cap = StrokeCap.Round
        )

        // Minute Hand
        val xRadMinute = cos(minuteAngle * Math.PI / 180F)
        val yRadMinute = sin(minuteAngle * Math.PI / 180F)
        val rMinute = rInnerCircle.minus(2.dp.toPx())
        drawLine(
            color = minuteHandColor,
            start = Offset(origin, origin),
            end = Offset(
                (origin + (rMinute * xRadMinute)).toFloat(),
                (origin + (rMinute * yRadMinute)).toFloat()
            ),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round
        )

        // Second Hand
        val xRadSecond = cos(secondAngle * Math.PI / 180F)
        val yRadSecond = sin(secondAngle * Math.PI / 180F)
        val rSecond = lineOuterR.minus(2.dp.toPx())
        drawLine(
            color = secondHandColor,
            start = Offset(origin, origin),
            end = Offset(
                (origin + (rSecond * xRadSecond)).toFloat(),
                (origin + (rSecond * yRadSecond)).toFloat()
            ),
            strokeWidth = 1.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}
