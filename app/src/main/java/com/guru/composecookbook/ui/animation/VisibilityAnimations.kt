package com.guru.composecookbook.ui.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.R
import com.guru.composecookbook.theme.blue500
import com.guru.composecookbook.theme.components.Material3Card
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.orange500
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText

@Composable
fun AnimationsWithVisibilityApi() {
    Spacer(modifier = Modifier.height(50.dp))
    TitleText(title = "Using Visibility Apis(Experimental)")
    AnimateVisibilityAnim()
    Divider()
    AnimateVisibilityWithDifferentChildAnimations()
    Divider()
    AnimateVisibilityWithSlideInOutSample()
    Divider()
    VisibilityAnimationWithShrinkExpand()
    Divider()
    AnimateContentSize()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateVisibilityAnim() {
    SubtitleText(subtitle = "Animate view visibility via  AnimateVisibility()")
    var expanded by remember { mutableStateOf(true) }
    FloatingActionButton(
        onClick = { expanded = !expanded },
        modifier = Modifier.padding(16.dp)
    ) {
        Row(Modifier.padding(start = 16.dp, end = 16.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = null
            )
            AnimatedVisibility(
                expanded,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(modifier = Modifier.padding(start = 8.dp), text = "Tweet")
            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateVisibilityWithDifferentChildAnimations() {
    SubtitleText(subtitle = "AnimateVisibility() with different child Animations")
    val colors = listOf(green500, blue500, orange500, purple)
    var expanded by remember { mutableStateOf(true) }
    IconButton(onClick = { expanded = !expanded }) {
        Icon(imageVector = Icons.Default.RemoveRedEye, contentDescription = "")
    }

    AnimatedVisibility(visible = expanded) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            colors.forEachIndexed { index, color ->

                val springAnim = remember {
                    spring<IntOffset>(
                        stiffness = Spring.StiffnessLow * (1f - index * 0.2f)
                    )
                }
                Material3Card(
                    backgroundColor = color, modifier = Modifier
                        .size(80.dp)
                        .padding
                            (8.dp)
                        .animateEnterExit(
                            enter = slideInHorizontally { it },
                            exit = ExitTransition.None
                        )
                ) {

                }
            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityAnimationWithShrinkExpand() {
    SubtitleText(subtitle = "Visibility animation with expand/shrink as enter/exit")
    var visibility by remember { mutableStateOf(true) }

    Row(
        Modifier
            .padding(12.dp)
            .width(200.dp)
            .height(60.dp)
            .background(green200)
            .clickable(onClick = { visibility = !visibility })
    ) {
        AnimatedVisibility(
            visibility,
            modifier = Modifier.align(Alignment.CenterVertically),
            enter = expandIn(expandFrom = Alignment.Center) { it -> it * 4 },
            exit = shrinkOut(shrinkTowards = Alignment.Center) { it -> it }
        ) {
            Button(
                modifier = Modifier.padding(start = 12.dp),
                onClick = { visibility = !visibility }) {
                Text(text = "Shrink/Expand")
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateVisibilityWithSlideInOutSample() {
    SubtitleText(subtitle = "Visibility animation with fade and slide as enter/exit")
    var visibility by remember { mutableStateOf(true) }
    Row(
        Modifier
            .padding(12.dp)
            .width(200.dp)
            .height(60.dp)
            .background(green500)
            .clickable(onClick = { visibility = !visibility })
    ) {
        AnimatedVisibility(
            visibility,
            enter = slideIn(
                tween(
                    easing = LinearOutSlowInEasing,
                    durationMillis = 500
                )
            ) { IntOffset(0, 120) },
            exit = slideOut(
                tween(500, easing = FastOutSlowInEasing),
            ) { IntOffset(0, 120) }
        ) {
            // Content that needs to appear/disappear goes here:
            Text("Tap for Sliding animation")
        }
    }
}

@Composable
fun AnimateContentSize() {
    SubtitleText(subtitle = "Using Modifier.animateContentSize() to animate content change")
    var count by remember { mutableStateOf(1) }

    Row(modifier = Modifier
        .animateContentSize()
        .clickable { if (count < 10) count += 3 else count = 1 }) {
        (0..count).forEach { _ ->
            Icon(imageVector = Icons.Default.PlayCircleFilled, contentDescription = null)
        }
    }
}
