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
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.ui.utils.RotateIcon
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText

@Composable
fun AnimationScreen() {
    var animateIcon by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.semantics { testTag = "Animation Screen" },
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
    val buttonColors = ButtonConstants.defaultButtonColors(
        backgroundColor = animate(color)
    )
    Button(
        onClick = { enabled.value = !enabled.value },
        colors = buttonColors,
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
    val buttonColors = ButtonConstants.defaultButtonColors(
        backgroundColor = animate(color)
    )
    Button(
        onClick = { enabled.value = !enabled.value },
        colors = buttonColors,
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
        modifier = Modifier.padding(12.dp).animateContentSize().background(green500)
            .clickable { enabled.value = !enabled.value },
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
            bitmap = imageResource(R.drawable.food10),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .preferredSize(animate(if (enabled.value) 100.dp else 250.dp))
                .padding(8.dp)
                .clickable { enabled.value = !enabled.value }
                .clip(RoundedCornerShape(animate(if (enabled.value) 0.dp else 8.dp)))
        )
        Image(
            bitmap = imageResource(R.drawable.food12),
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
    var colorState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    var colorFinalState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }

    val colorAnim = transition(
        definition = AnimationDefinitions.colorAnimDefinition,
        initState = colorState,
        toState = colorFinalState,
        onStateChangeFinished = {
            when (it) {
                AnimationDefinitions.AnimationState.START -> {
                    colorState = AnimationDefinitions.AnimationState.START
                    colorFinalState = AnimationDefinitions.AnimationState.MID
                }
                AnimationDefinitions.AnimationState.MID -> {
                    colorState = AnimationDefinitions.AnimationState.MID
                    colorFinalState = AnimationDefinitions.AnimationState.END
                }
                AnimationDefinitions.AnimationState.END -> {
                    colorState = AnimationDefinitions.AnimationState.END
                    colorFinalState = AnimationDefinitions.AnimationState.START
                }
            }
        }
    )
    val buttonColors = ButtonConstants.defaultButtonColors(
        backgroundColor = colorAnim[AnimationDefinitions.colorPropKey]
    )
    Button(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        colors = buttonColors,
        onClick = {}) {
        Text("Color prop Animations", modifier = Modifier.padding(8.dp))
    }
}

@Preview
@Composable
fun DpMultiStateAnimation() {
    val dpStartState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    val dpEndState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
    val dpAnim = transition(
        definition = AnimationDefinitions.dpAnimDefinition,
        initState = dpStartState,
        toState = dpEndState
    )

    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Card(modifier = Modifier.preferredSize(120.dp).padding(12.dp)) {
            Image(
                bitmap = imageResource(id = R.drawable.lana),
                modifier = Modifier.height(dpAnim[AnimationDefinitions.dpPropKey])
            )
        }
        Card(modifier = Modifier.preferredSize(120.dp).padding(12.dp)) {
            Image(
                bitmap = imageResource(id = R.drawable.billie),
                modifier = Modifier.height(100.dp - dpAnim[AnimationDefinitions.dpPropKey])
            )
        }
    }
}


@Preview
@Composable
fun FloatMutliStateAnimation() {
    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    val floatStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
    val floatAnim = transition(
        definition = AnimationDefinitions.floatAnimDefinition(0f, 100f, true),
        initState = floatStateStart,
        toState = floatStateFinal
    )

    Card(backgroundColor = Color.White, modifier = Modifier.preferredSize(150.dp)) {
        Image(
            bitmap = imageResource(id = R.drawable.lana),
            alpha = floatAnim[AnimationDefinitions.floatPropKey] / 100,
        )
    }

}

@Composable
fun FloatMultiStateAnimationCircleStrokeCanvas() {
    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    val floadStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
    val floatAnim = transition(
        definition = AnimationDefinitions.floatAnimDefinition(0f, 360f, true),
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
        var rotationAngle = floatAnim[AnimationDefinitions.floatPropKey] - 90f
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

@Preview
@Composable
fun FloatMultiStateAnimationCircleCanvas(color: Color = green500, radiusEnd: Float = 200f) {
    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    val floadStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
    val floatAnim = transition(
        definition = AnimationDefinitions.floatAnimDefinition(0f, radiusEnd, true),
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
        val radius = floatAnim[AnimationDefinitions.floatPropKey]
        drawCircle(
            color = color.copy(alpha = 0.8f),
            radius = radius,
            center = centerOffset,
        )
        drawCircle(
            color = color.copy(alpha = 0.4f),
            radius = radius / 2,
            center = centerOffset,
        )
        drawCircle(
            color = color.copy(alpha = 0.2f),
            radius = radius / 4,
            center = centerOffset,
        )
    }
}

@Composable
fun FloatMultiStateAnimationExplode(duration: Int = 500) {
    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
    val floadStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
    val floatAnim = transition(
        definition = AnimationDefinitions.floatAnimDefinition(
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
        var radius = floatAnim[AnimationDefinitions.floatPropKey]
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
        bitmap = imageResource(id = R.drawable.bp),
        modifier = modifier
    )

    Spacer(modifier = Modifier.padding(30.dp))
    var draw2 by remember { mutableStateOf(false) }

    Box {
        Image(
            bitmap = imageResource(id = R.drawable.adele21),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw2) 30f else 5f),
                translationX = animate(target = if (draw2) 320f else 0f),
                translationY = 0f,
            ).clickable(onClick = { draw2 = !draw2 })
        )
        Image(
            bitmap = imageResource(id = R.drawable.dualipa),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw2) 30f else 10f),
                translationX = animate(target = if (draw2) -320f else 0f),
                translationY = animate(target = if (draw2) 0f else 30f)
            ).clickable(onClick = { draw2 = !draw2 })
        )
        Image(
            bitmap = imageResource(id = R.drawable.edsheeran),
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
            bitmap = imageResource(id = R.drawable.wolves),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw3) 30f else 5f),
                translationX = animate(target = if (draw3) 320f else 0f),
                rotationY = animate(target = if (draw3) 45f else 0f),
                translationY = 0f
            ).clickable(onClick = { draw3 = !draw3 })
        )
        Image(
            bitmap = imageResource(id = R.drawable.sam),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw3) 30f else 10f),
                translationX = animate(target = if (draw3) -320f else 0f),
                rotationY = animate(target = if (draw3) 45f else 0f),
                translationY = animate(target = if (draw3) 0f else 30f)
            ).clickable(onClick = { draw3 = !draw3 })
        )
        Image(
            bitmap = imageResource(id = R.drawable.billie),
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
            bitmap = imageResource(id = R.drawable.imagindragon),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw4) 30f else 5f),
                translationX = animate(target = if (draw4) 320f else 0f),
                rotationZ = animate(target = if (draw4) 45f else 0f),
                translationY = 0f
            ).clickable(onClick = { draw4 = !draw4 })
        )
        Image(
            bitmap = imageResource(id = R.drawable.khalid),
            modifier = Modifier.preferredSize(150.dp).drawLayer(
                shadowElevation = animate(if (draw4) 30f else 10f),
                translationX = animate(target = if (draw4) -320f else 0f),
                rotationZ = animate(target = if (draw4) 45f else 0f),
                translationY = animate(target = if (draw4) 0f else 30f)
            ).clickable(onClick = { draw4 = !draw4 })
        )
        Image(
            bitmap = imageResource(id = R.drawable.camelia),
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
    val moveX = -1000f
    val moveXMax = 1000f
    TitleText("Animated Value to Animations + drag")
    val xFloat = animatedFloat(initVal = moveX)

    val dragObserver = object : DragObserver {
        override fun onStart(downPosition: Offset) {
            xFloat.setBounds(moveX, moveXMax)
            super.onStart(downPosition)
        }

        override fun onStop(velocity: Offset) {
            xFloat.snapTo(moveX)
            super.onStop(velocity)
        }

        override fun onCancel() {
            xFloat.snapTo(moveX)
            super.onCancel()
        }

        override fun onDrag(dragDistance: Offset): Offset {
            xFloat.animateTo(xFloat.targetValue + dragDistance.x)
            return super.onDrag(dragDistance)
        }
    }

    CardElement(
        modifier = Modifier.rawDragGestureFilter(dragObserver)
            .preferredSize(200.dp).drawLayer(
                translationX = xFloat.value,
            )
    )
}

@Composable
fun CardElement(modifier: Modifier = Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {}
}





