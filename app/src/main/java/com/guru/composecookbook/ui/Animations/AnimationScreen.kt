package com.guru.composecookbook.ui.Animations

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.rawDragGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.teal200
import com.guru.composecookbook.ui.utils.RotateIcon
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText

@Composable
fun AnimationScreen() {
    var animateIcon by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Animations") },
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = { animateIcon = !animateIcon }) {
                        RotateIcon(
                            state = animateIcon,
                            asset = Icons.Filled.PlayArrow,
                            angle = 1440f,
                            duration = 3000
                        )
                    }
                }
            )
        },
        bodyContent = {
            AnimationScreenContent()
        }
    )
}

@Composable
fun AnimationScreenContent() {
    ScrollableColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.padding(4.dp))
        TitleText(title = "Single value animations")
        SubtitleText(subtitle = "animate(Color)")
        SimpleColorAnimation()
        Spacer(modifier = Modifier.padding(4.dp))
        SubtitleText(subtitle = "animate(Color)+animate(Dp)")
        SingleScaleAndColorAnimation()
        Spacer(modifier = Modifier.padding(4.dp))
        SubtitleText(subtitle = "animate(Dp)")
        SingleImageScaleAnimation()
        Spacer(modifier = Modifier.padding(4.dp))
        SubtitleText(subtitle = "animateContentSize()")
        SingleAnimationContent()
        Spacer(modifier = Modifier.padding(8.dp))
        TitleText("Visibility Animations: Experimental`")
        Spacer(modifier = Modifier.padding(8.dp))
        VisibilityAnimationFAB()
        Spacer(modifier = Modifier.padding(8.dp))
        VisibilityAnimationFade()
        Spacer(modifier = Modifier.padding(8.dp))
        VisibilityAnimationShrinkExpand()
        Spacer(modifier = Modifier.padding(8.dp))
        SlideInOutSample()
        Spacer(modifier = Modifier.padding(8.dp))
        TitleText("Multi State Animations")
        Spacer(modifier = Modifier.padding(8.dp))
        SubtitleText(subtitle = "Three different colorPropKey state with repeat")
        ColorMultistateAnimation()
        Spacer(modifier = Modifier.padding(8.dp))
        SubtitleText(subtitle = "Different DpPropKey value states animation")
        DpMultiStateAnimation()
        Spacer(modifier = Modifier.padding(8.dp))
        SubtitleText(subtitle = "Different FloatPropKey value states animation")
        FloatMutliStateAnimation()
        Spacer(modifier = Modifier.padding(8.dp))
        TitleText("Multi State Animations on Canvas")
        val ripple = remember { mutableStateOf(false) }
        if (ripple.value) {
            FloatMultiStateAnimationExplode(500)
        }
        Button(onClick = { ripple.value = !ripple.value }) {
            Text(text = "Top top explode")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        SubtitleText(subtitle = "Circle stroke canvas drawing + rotation animation")
        FloatMultiStateAnimationCircleStrokeCanvas()
        Spacer(modifier = Modifier.padding(30.dp))
        SubtitleText(subtitle = "Multiple Circle canvas+ Scaling radius")
        FloatMultiStateAnimationCircleCanvas()
        Spacer(modifier = Modifier.padding(50.dp))
        //draw layer animations
        DrawLayerAnimations()
        Spacer(modifier = Modifier.padding(50.dp))
        //Animated Values animation
        AnimatedValuesAnimations()
        Spacer(modifier = Modifier.padding(50.dp))
    }
}

@Composable
fun SimpleColorAnimation() {
    val enabled = remember { mutableStateOf(true) }
    val color = if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    Button(
        onClick = { enabled.value = !enabled.value },
        backgroundColor = animate(color),
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Text("Color Animation")
    }
}

@Composable
fun SingleScaleAndColorAnimation() {
    val enabled = remember { mutableStateOf(true) }
    val color = if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    val height = if (enabled.value) 40.dp else 60.dp
    val width = if (enabled.value) 150.dp else 300.dp
    Button(
        onClick = { enabled.value = !enabled.value },
        backgroundColor = animate(color),
        modifier = Modifier
            .padding(16.dp)
            .preferredHeight(animate(height))
            .preferredWidth(animate(width)),
    ) {
        Text("Scale & Color")
    }
}

@Composable
fun SingleAnimationContent() {
    val enabled = remember { mutableStateOf(true) }
    Box(
        modifier = Modifier.padding(12.dp).animateContentSize()
            .clickable { enabled.value = !enabled.value },
        backgroundColor = Color.Green,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            if (enabled.value) "Auto animate for child content size changes using animateContentSize() for Read More" else DemoDataProvider.longText,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun SingleImageScaleAnimation() {
    val enabled = remember { mutableStateOf(true) }
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp)) {
        Image(
            asset = imageResource(R.drawable.food10),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .preferredSize(animate(if (enabled.value) 100.dp else 250.dp))
                .padding(8.dp)
                .clickable { enabled.value = !enabled.value }
                .clip(RoundedCornerShape(animate(if (enabled.value) 0.dp else 8.dp)))
        )
        Image(
            asset = imageResource(R.drawable.food12),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .preferredSize(animate(if (!enabled.value) 100.dp else 250.dp))
                .padding(8.dp)
                .clickable { enabled.value = !enabled.value }
                .clip(RoundedCornerShape(animate(if (!enabled.value) 0.dp else 8.dp)))
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityAnimationFAB() {
    var expanded by remember { mutableStateOf(true) }
    FloatingActionButton(
        onClick = { expanded = !expanded },
        modifier = Modifier
    ) {
        Row(Modifier.padding(start = 16.dp, end = 16.dp)) {
            Icon(
                vectorResource(id = R.drawable.ic_twitter),
                Modifier.align(Alignment.CenterVertically)
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
fun VisibilityAnimationFade() {
    var visibility by remember { mutableStateOf(true) }
    Row(
        Modifier.padding(start = 12.dp, end = 12.dp).width(200.dp).height(60.dp)
            .background(green500).clickable(onClick = { visibility = !visibility })
    ) {
        AnimatedVisibility(
            visibility,
            modifier = Modifier.align(Alignment.CenterVertically),
            enter = fadeIn(1f),
            exit = fadeOut(0f)
        ) {
            Text(modifier = Modifier.padding(start = 12.dp), text = "Tap to Fade In Out")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityAnimationShrinkExpand() {
    var visibility by remember { mutableStateOf(true) }
    Row(
        Modifier.padding(start = 12.dp, end = 12.dp).width(200.dp).height(60.dp)
            .background(green500).clickable(onClick = { visibility = !visibility })
    ) {
        AnimatedVisibility(
            visibility,
            modifier = Modifier.align(Alignment.CenterVertically),
            enter = expandIn(Alignment.Center, { fullSize: IntSize -> fullSize * 4 }),
            exit = shrinkOut(Alignment.Center)
        ) {
            Text(modifier = Modifier.padding(start = 12.dp), text = "Tap to Shrink expand")
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SlideInOutSample() {
    var visibility by remember { mutableStateOf(true) }
    Row(
        Modifier.padding(start = 12.dp, end = 12.dp).width(200.dp).height(60.dp)
            .background(green500).clickable(onClick = { visibility = !visibility })
    ) {
        AnimatedVisibility(
            visibility,
            enter = slideIn(
                { fullSize -> IntOffset(0, 100) },
                tween(500, easing = LinearOutSlowInEasing)
            ),
            exit = slideOut(
                { IntOffset(0, 50) },
                tween(500, easing = FastOutSlowInEasing)

            )
        ) {
            // Content that needs to appear/disappear goes here:
            Text("Tap for Sliding animation")
        }
    }
}


@Composable
fun ColorMultistateAnimation() {
    var colorState by remember { mutableStateOf(0) }
    var colorFinalState by remember { mutableStateOf(2) }

    val colorAnim = transition(
        definition = AnimationDefinations.colorAnimDefinition,
        initState = colorState,
        toState = colorFinalState,
        onStateChangeFinished = {
            when (it) {
                0 -> {
                    colorState = 0
                    colorFinalState = 1
                }
                1 -> {
                    colorState = 1
                    colorFinalState = 2
                }
                2 -> {
                    colorState = 2
                    colorFinalState = 0
                }
            }
        }
    )
    Button(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        backgroundColor = colorAnim[AnimationDefinations.colorPropKey],
        onClick = {}) {
        Text("Color prop Animations", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun DpMultiStateAnimation() {
    var dpStartState by remember { mutableStateOf(0) }
    var dpEndState by remember { mutableStateOf(2) }
    val dpAnim = transition(
        definition = AnimationDefinations.dpAnimDefinition,
        initState = dpStartState,
        toState = dpEndState
    )

    Card(modifier = Modifier.preferredSize(200.dp), backgroundColor = green200) {
        Button(
            onClick = {},
            modifier = Modifier.height(dpAnim[AnimationDefinations.dpPropKey]).padding(8.dp)
        ) {
            Text(text = "DP Prop Animations")
        }
    }
    Card(modifier = Modifier.preferredSize(100.dp).padding(12.dp)) {
        Image(
            asset = imageResource(id = R.drawable.p1),
            modifier = Modifier.height(dpAnim[AnimationDefinations.dpPropKey])
        )
    }
}

@Composable
fun FloatMutliStateAnimation() {
    var floatStateStart by remember { mutableStateOf(0) }
    var floatStateFinal by remember { mutableStateOf(2) }
    val floatAnim = transition(
        definition = AnimationDefinations.floatAnimDefinition(0f, 100f, true),
        initState = floatStateStart,
        toState = floatStateFinal
    )

    Card(backgroundColor = green500, modifier = Modifier.preferredSize(150.dp)) {
        Image(
            asset = imageResource(id = R.drawable.p2),
            alpha = floatAnim[AnimationDefinations.floatPropKey]
        )
    }

}

@Composable
fun FloatMultiStateAnimationCircleStrokeCanvas() {
    var floatStateStart by remember { mutableStateOf(0) }
    var floadStateFinal by remember { mutableStateOf(2) }
    val floatAnim = transition(
        definition = AnimationDefinations.floatAnimDefinition(0f, 360f, true),
        initState = floatStateStart,
        toState = floadStateFinal
    )
    val stroke = Stroke(8f)
    Canvas(modifier = Modifier.padding(16.dp).preferredSize(100.dp)) {
        val diameter = size.minDimension
        val radius = diameter / 2f
        val insideRadius = radius - stroke.width
        val topLeftOffset = Offset(
            10f,
            10f
        )
        val size = Size(insideRadius * 2, insideRadius * 2)
        var rotationAngle = floatAnim[AnimationDefinations.floatPropKey] - 90f
        drawArc(
            color = green500,
            startAngle = rotationAngle,
            sweepAngle = 150f,
            topLeft = topLeftOffset,
            size = size,
            useCenter = false,
            style = stroke,
        )
        rotationAngle += 40
    }
}

@Composable
fun FloatMultiStateAnimationCircleCanvas() {
    var floatStateStart by remember { mutableStateOf(0) }
    var floadStateFinal by remember { mutableStateOf(2) }
    val floatAnim = transition(
        definition = AnimationDefinations.floatAnimDefinition(0f, 200f, true),
        initState = floatStateStart,
        toState = floadStateFinal
    )

    Canvas(modifier = Modifier.padding(16.dp)) {
        val centerOffset = Offset(
            10f,
            10f
        )
        val centerOffset2 = Offset(
            220f,
            10f
        )
        var radius = floatAnim[AnimationDefinations.floatPropKey]
        drawCircle(
            color = green200,
            radius = radius,
            center = centerOffset,
        )
        drawCircle(
            color = green500,
            radius = radius / 2,
            center = centerOffset,
        )
        drawCircle(
            color = teal200,
            radius = radius / 4,
            center = centerOffset,
        )
    }
}

@Composable
fun FloatMultiStateAnimationExplode(duration: Int = 500) {
    var floatStateStart by remember { mutableStateOf(0) }
    var floadStateFinal by remember { mutableStateOf(2) }
    val floatAnim = transition(
        definition = AnimationDefinations.floatAnimDefinition(
            0f, 2000f, false, duration
        ),
        initState = floatStateStart,
        toState = floadStateFinal
    )

    Canvas(modifier = Modifier) {
        val centerOffset = Offset(
            10f,
            10f
        )
        var radius = floatAnim[AnimationDefinations.floatPropKey]
        drawCircle(
            color = green200.copy(alpha = 0.8f),
            radius = radius,
            center = centerOffset,
        )
        radius += 500
    }
}

@Composable
private fun DrawLayerAnimations() {
    TitleText("DrawLayer changes + Single value animations")
    var draw by remember { mutableStateOf(false) }
    val modifier = Modifier.preferredSize(150.dp).drawLayer(
        scaleX = animate(if (draw) 2f else 1f),
        scaleY = animate(if (draw) 2f else 1f),
        shadowElevation = animate(if (draw) 50f else 5f),
        clip = draw,
        rotationZ = animate(if (draw) 360f else 0f)
    ).clickable(onClick = { draw = !draw })

    Image(
        asset = imageResource(id = R.drawable.bp),
        modifier = modifier
    )

    Spacer(modifier = Modifier.padding(30.dp))
    var draw2 by remember { mutableStateOf(false) }

    Box {
        Image(
            asset = imageResource(id = R.drawable.adele21),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw2) 30f else 5f),
                translationX = animate(target = if (draw2) 320f else 0f),
                translationY = 0f,
            ).clickable(onClick = { draw2 = !draw2 })
        )
        Image(
            asset = imageResource(id = R.drawable.dualipa),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw2) 30f else 10f),
                translationX = animate(target = if (draw2) -320f else 0f),
                translationY = animate(target = if (draw2) 0f else 30f)
            ).clickable(onClick = { draw2 = !draw2 })
        )
        Image(
            asset = imageResource(id = R.drawable.edsheeran),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw2) 30f else 5f),
                translationY = animate(target = if (draw2) 0f else 50f)
            ).clickable(onClick = { draw2 = !draw2 })
        )
    }
    Spacer(modifier = Modifier.padding(30.dp))
    var draw3 by remember { mutableStateOf(false) }

    Box {
        Image(
            asset = imageResource(id = R.drawable.wolves),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw3) 30f else 5f),
                translationX = animate(target = if (draw3) 320f else 0f),
                rotationY = animate(target = if (draw3) 45f else 0f),
                translationY = 0f
            ).clickable(onClick = { draw3 = !draw3 })
        )
        Image(
            asset = imageResource(id = R.drawable.sam),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw3) 30f else 10f),
                translationX = animate(target = if (draw3) -320f else 0f),
                rotationY = animate(target = if (draw3) 45f else 0f),
                translationY = animate(target = if (draw3) 0f else 30f)
            ).clickable(onClick = { draw3 = !draw3 })
        )
        Image(
            asset = imageResource(id = R.drawable.billie),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw3) 30f else 5f),
                translationY = animate(target = if (draw3) 0f else 50f),
                rotationY = animate(target = if (draw3) 45f else 0f)
            ).clickable(onClick = { draw3 = !draw3 })
        )
    }
    Spacer(modifier = Modifier.padding(30.dp))
    var draw4 by remember { mutableStateOf(false) }

    Box {
        Image(
            asset = imageResource(id = R.drawable.imagindragon),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw4) 30f else 5f),
                translationX = animate(target = if (draw4) 320f else 0f),
                rotationZ = animate(target = if (draw4) 45f else 0f),
                translationY = 0f
            ).clickable(onClick = { draw4 = !draw4 })
        )
        Image(
            asset = imageResource(id = R.drawable.khalid),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw4) 30f else 10f),
                translationX = animate(target = if (draw4) -320f else 0f),
                rotationZ = animate(target = if (draw4) 45f else 0f),
                translationY = animate(target = if (draw4) 0f else 30f)
            ).clickable(onClick = { draw4 = !draw4 })
        )
        Image(
            asset = imageResource(id = R.drawable.camelia),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw4) 30f else 5f),
                translationY = animate(target = if (draw4) 0f else 50f),
                rotationZ = animate(target = if (draw4) 45f else 0f)
            ).clickable(onClick = { draw4 = !draw4 })
        )
    }
}

@Composable
fun AnimatedValuesAnimations() {
    val moveX = 0f
    val moveXMax = 1000f
    val moveY = 0f
    val moveYMax = 1000f
    TitleText("Animated Values Animations")
    val xFloat = animatedFloat(initVal = moveX)
    val yFloat = animatedFloat(initVal = moveX)

    val dragObserver = object : DragObserver {
        override fun onStart(downPosition: Offset) {
            xFloat.setBounds(moveX, moveXMax)
            yFloat.setBounds(moveY, moveYMax)
            super.onStart(downPosition)
        }

        override fun onStop(velocity: Offset) {
            xFloat.snapTo(moveX)
            yFloat.snapTo(moveY)
            super.onStop(velocity)
        }

        override fun onCancel() {
            xFloat.snapTo(moveX)
            yFloat.snapTo(moveY)
            super.onCancel()
        }

        override fun onDrag(dragDistance: Offset): Offset {
            xFloat.animateTo(xFloat.targetValue + dragDistance.x)
            yFloat.animateTo(yFloat.targetValue + dragDistance.y)
            return super.onDrag(dragDistance)
        }
    }
    val animatedRadius = Dp(xFloat.value)
    CardElement(
        modifier = Modifier.rawDragGestureFilter(dragObserver)
            .preferredSize(200.dp).drawLayer(
                translationX = xFloat.value,
                translationY = yFloat.value
            )
    )
}

@Composable
fun CardElement(modifier: Modifier = Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {

    }
}





