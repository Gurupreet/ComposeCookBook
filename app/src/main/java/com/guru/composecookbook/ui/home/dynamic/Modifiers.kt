package com.guru.composecookbook.ui.home.dynamic

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.gesture.doubleTapGestureFilter
import androidx.compose.ui.gesture.longPressGestureFilter
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.teal200
import com.guru.composecookbook.theme.typography

@Composable
fun HowToModifiers() {
    ScrollableColumn {
        Text(
            text = "Order in modifier values matters",
            style = typography.h6,
            modifier = Modifier.padding(8.dp)
        )
        DemoText("No Modifiers")
        DemoElementButton(modifier = Modifier)

        DemoText("Modifier.fillMaxWidth")
        DemoElementButton(modifier = Modifier.fillMaxWidth())
        DemoText("Modifier.fillMaxWidth.padding(12)")
        DemoElementButton(modifier = Modifier.fillMaxWidth().padding(12.dp))
        DemoText("Modifier.preferredSize(100dp)")
        DemoElementButton(modifier = Modifier.preferredSize(100.dp))
        DemoText("Modifier.height(50).width(200)")
        DemoElementButton(modifier = Modifier.height(50.dp).width(200.dp))

        DemoText("Modifier.clip(CircleShare)")
        DemoElementButton(modifier = Modifier.clip(CircleShape))
        DemoText("Modifier.clip(RoundedCornerShape(12))")
        DemoElementButton(modifier = Modifier.clip(RoundedCornerShape(12.dp)))
        DemoText("Modifier.clip(RoundedCornerShape(topLeft = 12.dp, bottomRight = 12.dp))")
        DemoElementButton(
            modifier = Modifier.clip(RoundedCornerShape(topLeft = 12.dp, bottomRight = 12.dp))
        )
        DemoText("Modifier.clip(CutCornerShape(12))")
        DemoElementButton(modifier = Modifier.clip(CutCornerShape(12.dp)))

        DemoText("Modifier.align(Alignment.CenterHorizontally)")
        DemoElementButton(modifier = Modifier.align(Alignment.CenterHorizontally))
        DemoText("Modifier.align(Alignment.End)")
        DemoElementButton(modifier = Modifier.align(Alignment.End))

        DemoText("Modifier.alpha0.5f)")
        DemoElementButton(modifier = Modifier.alpha(0.5f))
        DemoText("Modifier.drawShadow(12.dp)")
        DemoElementButton(modifier = Modifier.shadow(12.dp))
        DemoText("Modifier.background(MaterialTheme.colors.secondary))")
        DemoElementButton(modifier = Modifier.background(MaterialTheme.colors.secondary))
        DemoText("Modifier.padding(8.dp).background(brush = HorizontalGradient)")
        DemoElementText(
            modifier = Modifier.padding(8.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(teal200, green500),
                        startX = 0f,
                        endX = 100f
                    )
                )
        )
        DemoText("Modifier.background(brush = HorizontalGradient).padding(8.dp)")
        DemoElementText(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(teal200, green500),
                        startX = 0f,
                        endX = 200f
                    )
                ).padding(8.dp)
        )
        DemoText("Modifier.background(brush = VerticalGradient).padding(8.dp)")
        DemoElementText(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(teal200, green500),
                        startY = 0f,
                        endY = 500f
                    )
                ).padding(8.dp)
        )

        DemoText("Modifier.background(teal200).padding(8.dp).clickable(onClick = {})")
        DemoElementText(
            modifier = Modifier.background(teal200).padding(8.dp).clickable(onClick = {})
        )
        DemoText("Modifier.clickable(onClick = {}).background(teal200).padding(8.dp)")
        DemoElementText(
            modifier = Modifier.clickable(onClick = {}).background(teal200).padding(8.dp)

        )
        DemoText("Add double tap: Modifier.doubleTapGestureFilter{ } ")
        DemoElementText(modifier = Modifier.background(teal200).doubleTapGestureFilter { }
            .padding(8.dp))
        DemoText("Add long press: Modifier.doubleTapGestureFilter{ } ")
        DemoElementText(modifier = Modifier.background(teal200).longPressGestureFilter { }
            .padding(8.dp))

    }
}


@Composable
fun DemoElementButton(modifier: Modifier) {
    Button(onClick = {}, modifier = modifier) {
        Text(text = "Basic Button")
    }
}

@Composable
fun DemoElementText(modifier: Modifier) {
    Text(text = "Basic Text", modifier = modifier)
}

@Composable
fun DemoText(text: String) {
    Text(
        text = text,
        style = typography.body2.copy(Color.Gray),
        modifier = Modifier.padding(4.dp)
    )
}

@Preview
@Composable
fun PreviewHowToModifiers() {
    HowToModifiers()
}