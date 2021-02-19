import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.utils.RotateIcon
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green500
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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
    LazyColumn(
        state = rememberLazyListState(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {  Spacer(modifier = Modifier.padding(4.dp)) }
        item { TitleText(title = "State Animations(Fire and forget)") }
        item { AnimationsForStates() }
        item { AnimationsWithVisibilityApi() }
        item { AnimatableSuspendedAnimations() }
      //  ColorPicker(onColorSelected = { /*TODO*/ })
        item { Spacer(modifier = Modifier.padding(100.dp)) }
    }
}

@Composable
fun AnimationsForStates() {
    SimpleColorStateAnimation()
    Divider()
    SimpleDpStateAnimations()
    Divider()
    SimpleFloatStateAnimation()
    Divider()
    SimpleOffsetStateAnimation()
    Divider()
    SimpleAnimateCustomStateClass()
    Divider()
    DrawLayerWithAnimateAsStateAnimations()
    Spacer(modifier = Modifier.height(60.dp))
    Divider()
}

@Composable
fun AnimationsWithVisibilityApi() {
    Spacer(modifier = Modifier.height(50.dp))
    TitleText(title = "Using Visibility Apis(Experimental)")
    AnimateVisibilityAnim()
    Divider()
    AnimateVisibilityWithSlideInOutSample()
    Divider()
    VisibilityAnimationWithShrinkExpand()
    Divider()
    AnimateContentSize()
}

@Composable
fun AnimatableSuspendedAnimations() {
    TitleText(title = "Suspending Animations via Animatable(floatValue) with Bounds")
    SubtitleText(subtitle = "Use it with PointerInput or LaunchedEffects")
    SimpleDismissUsingAnimatable()
    Divider()
    PointerInputWithAnimateable()
    Divider()
    DraggableCardWithAnimatable()
    Divider()
}

@Composable
fun SimpleColorStateAnimation() {
    SubtitleText(subtitle = "Animate color")
    val enabled = remember { mutableStateOf(true) }
    val animatedColor = animateColorAsState(targetValue =
    if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)

    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = animatedColor.value
    )

    Button(
    onClick = { enabled.value = !enabled.value },
    colors = buttonColors,
    modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Text("Color Animation")
    }
}
//
@Composable
fun SimpleDpStateAnimations() {
    SubtitleText(subtitle = "Animate DP value")
    var enabled by remember { mutableStateOf(true) }
    val animatedColorState = animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
    val animatedHeightState = animateDpAsState(targetValue = if (enabled) 40.dp else 60.dp)
    val animatedWidthState = animateDpAsState(if (enabled) 150.dp else 300.dp)
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = animatedColorState.value
    )
    Button(
        onClick = { enabled = !enabled },
        colors = buttonColors,
        modifier = Modifier
            .padding(16.dp)
            .height(animatedHeightState.value)
            .preferredWidth(animatedWidthState.value),
    ) {
        Text("Scale & Color")
    }
}

@Composable
fun SimpleFloatStateAnimation() {
    SubtitleText(subtitle = "Animate Float value")

    var enabled by remember { mutableStateOf(true) }
    val animatedFloatState = animateFloatAsState(targetValue = if (enabled) 1f else 0.5f)
    Button(
        onClick = { enabled = !enabled },
        modifier = Modifier
            .padding(16.dp)
            .alpha(animatedFloatState.value)
    ) {
        Text("Opacity change")
    }
}

@Composable
fun SimpleOffsetStateAnimation() {
    SubtitleText(subtitle = "Animate Offset x,y value")
    Spacer(modifier = Modifier.height(20.dp))
    var enabled by remember { mutableStateOf(true) }
    val animatedOffset by animateOffsetAsState(
        targetValue = if (enabled) Offset(0f, 0f) else Offset(50f, 40f))
    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Image(
            painterResource(id = R.drawable.p1),
            contentDescription = null,
            modifier = Modifier
                .preferredSize(100.dp)
                .padding(16.dp)
                .offset(x = Dp(animatedOffset.x), y = Dp(animatedOffset.y))
                .clickable { enabled = !enabled }
        )
        Image(
            painterResource(id = R.drawable.p2),
            contentDescription = null,
            modifier = Modifier
                .preferredSize(100.dp)
                .padding(16.dp)
                .offset(x = -Dp(animatedOffset.x), y = -Dp(animatedOffset.y))
                .clickable { enabled = !enabled }
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

data class CustomAnimationState(val width: Dp, val rotation: Float)
@Composable
fun SimpleAnimateCustomStateClass() {
    SubtitleText(subtitle = "Animate Custom Class State with 2D vector")
    //TODO somehow not animating so investigate
    var enabled by remember { mutableStateOf(true) }
    val initUiState = CustomAnimationState(200.dp, 0f)
    val targetUiState = CustomAnimationState(300.dp, 15f)

    val uiState = if (enabled) initUiState else targetUiState

    val animatedUiState by animateValueAsState(
        targetValue = uiState,
        typeConverter = TwoWayConverter(
            convertToVector = { AnimationVector2D(it.width.value, it.rotation) },
            convertFromVector = { CustomAnimationState(it.v1.dp, it.v2)}
        ),
        animationSpec = tween(600)
    )

    Button(
        onClick = { enabled = !enabled },
        modifier = Modifier
            .padding(16.dp)
            .width(animatedUiState.width)
            .rotate(animatedUiState.rotation)
    ) {
        Text("Custom State Animation")
    }

    SubtitleText("You can also use Size, Int, Rect using AnimatesAsState.")
}

@Composable
fun DrawLayerWithAnimateAsStateAnimations() {
    TitleText(title = "Float state Animations on graphicsLayer")
    var draw by remember { mutableStateOf(false) }
    val modifier = Modifier.preferredSize(150.dp).graphicsLayer(
        scaleX = animateFloatAsState(if (draw) 2f else 1f).value,
        scaleY = animateFloatAsState(if (draw) 2f else 1f).value,
        shadowElevation = animateFloatAsState(if (draw) 50f else 5f).value,
        clip = draw,
        rotationZ = animateFloatAsState(if (draw) 360f else 0f).value
    ).clickable(onClick = { draw = !draw })

    Image(
        painter = painterResource(id = R.drawable.bp),
        contentDescription = null,
        modifier = modifier
    )

    Spacer(modifier = Modifier.padding(30.dp))
    var draw2 by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.adele21),
            contentDescription = null,
            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
                shadowElevation = animateFloatAsState(if (draw2) 30f else 5f).value,
                translationX = animateFloatAsState(if (draw2) 320f else 0f).value,
                translationY = 0f,
            ).clickable(onClick = { draw2 = !draw2 })
        )
        Image(
            painter = painterResource(id = R.drawable.dualipa),
            contentDescription = null,
            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
                shadowElevation = animateFloatAsState(if (draw2) 30f else 10f).value,
                translationX = animateFloatAsState(if (draw2) -320f else 0f).value,
                translationY = animateFloatAsState(if (draw2) 0f else 30f).value
            ).clickable(onClick = { draw2 = !draw2 })
        )
        Image(
            painter = painterResource(id = R.drawable.edsheeran),
            contentDescription = null,
            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
                shadowElevation = animateFloatAsState(if (draw2) 30f else 5f).value,
                translationY = animateFloatAsState(if (draw2) 0f else 50f).value
            ).clickable(onClick = { draw2 = !draw2 })
        )
    }
    Spacer(modifier = Modifier.padding(30.dp))
    var draw3 by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.wolves),
            contentDescription = null,
            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
                shadowElevation = animateFloatAsState(if (draw3) 30f else 5f).value,
                translationX = animateFloatAsState(if (draw3) 320f else 0f).value,
                rotationY = animateFloatAsState(if (draw3) 45f else 0f).value,
                translationY = 0f
            ).clickable(onClick = { draw3 = !draw3 })
        )
        Image(
            painter = painterResource(id = R.drawable.sam),
            contentDescription = null,
            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
                shadowElevation = animateFloatAsState(if (draw3) 30f else 10f).value,
                translationX = animateFloatAsState(if (draw3) -320f else 0f).value,
                rotationY = animateFloatAsState(if (draw3) 45f else 0f).value,
                translationY = animateFloatAsState(if (draw3) 0f else 30f).value
            ).clickable(onClick = { draw3 = !draw3 })
        )
        Image(
            painter = painterResource(id = R.drawable.billie),
            contentDescription = null,
            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
                shadowElevation = animateFloatAsState(if (draw3) 30f else 5f).value,
                translationY = animateFloatAsState(if (draw3) 0f else 50f).value,
                rotationY = animateFloatAsState(if (draw3) 45f else 0f).value
            ).clickable(onClick = { draw3 = !draw3 })
        )
    }
    Spacer(modifier = Modifier.padding(30.dp))
    var draw4 by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.imagindragon),
            contentDescription = null,
            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
                shadowElevation = animateFloatAsState(if (draw4) 30f else 5f).value,
                translationX = animateFloatAsState(if (draw4) 320f else 0f).value,
                rotationZ = animateFloatAsState(if (draw4) 45f else 0f).value,
                translationY = 0f
            ).clickable(onClick = { draw4 = !draw4 })
        )
        Image(
            painter = painterResource(id = R.drawable.khalid),
            contentDescription = null,
            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
                shadowElevation = animateFloatAsState(if (draw4) 30f else 10f).value,
                translationX = animateFloatAsState(if (draw4) -320f else 0f).value,
                rotationZ = animateFloatAsState(if (draw4) 45f else 0f).value,
                translationY = animateFloatAsState(if (draw4) 0f else 30f).value
            ).clickable(onClick = { draw4 = !draw4 })
        )
        Image(
            painter = painterResource(id = R.drawable.camelia),
            contentDescription = null,
            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
                shadowElevation = animateFloatAsState(if (draw4) 30f else 5f).value,
                translationY = animateFloatAsState(if (draw4) 0f else 50f).value,
                rotationZ = animateFloatAsState(if (draw4) 45f else 0f).value,
            ).clickable(onClick = { draw4 = !draw4 })
        )
        Spacer(modifier = Modifier.padding(60.dp))
    }
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
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun VisibilityAnimationFade() {
//    var visibility by remember { mutableStateOf(true) }
//    Row(
//        Modifier.padding(start = 12.dp, end = 12.dp).width(200.dp).height(60.dp)
//            .background(green500).clickable(onClick = { visibility = !visibility })
//    ) {
//        AnimatedVisibility(
//            visibility,
//            modifier = Modifier.align(Alignment.CenterVertically),
//            enter = fadeIn(1f),
//            exit = fadeOut(0f)
//        ) {
//            Text(modifier = Modifier.padding(start = 12.dp), text = "Tap to Fade In Out")
//        }
//    }
//}
//
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityAnimationWithShrinkExpand() {
    SubtitleText(subtitle = "Visibility animation with expand/shrink as enter/exit")
    var visibility by remember { mutableStateOf(true) }

    Row(
        Modifier.padding(12.dp).width(200.dp).height(60.dp)
            .background(green200).clickable(onClick = { visibility = !visibility })
    ) {
        AnimatedVisibility(
            visibility,
            modifier = Modifier.align(Alignment.CenterVertically),
            enter = expandIn(Alignment.Center, { fullSize: IntSize -> fullSize * 4 }),
            exit = shrinkOut(Alignment.Center)
        ) {
            Button(modifier = Modifier.padding(start = 12.dp), onClick = {visibility = !visibility}) {
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
        Modifier.padding(12.dp).width(200.dp).height(60.dp)
            .background(green500).clickable(onClick = { visibility = !visibility })
    ) {
        AnimatedVisibility(
            visibility,
            enter = slideIn(
                { IntOffset(0, 120) },
                tween(500, easing = LinearOutSlowInEasing)
            ) + fadeIn(1f,  tween(500, easing = LinearOutSlowInEasing)),
            exit = slideOut(
                { IntOffset(0, 120) },
                tween(500, easing = FastOutSlowInEasing)
            ) + fadeOut(0.5f,  tween(500, easing = LinearOutSlowInEasing))
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

    Row(modifier = Modifier.animateContentSize().clickable { if (count < 10) count += 3 else count = 1 }) {
        (0..count).forEach {
            Icon(imageVector = Icons.Default.PlayCircleFilled, contentDescription = null)
        }
    }
}

@Composable
fun SimpleDismissUsingAnimatable() {
    val animatableValue = remember { Animatable(0f) }
    animatableValue.updateBounds(-100f, 100f)
    var enable by remember { mutableStateOf(true) }
    Card(
        backgroundColor = green200,
        modifier = Modifier.size(100.dp).offset(x = Dp(animatableValue.value)).clickable { enable = !enable },
    ) {
        LaunchedEffect(enable) {
            if (enable) {
                animatableValue.animateTo(100f)
            } else {
                animatableValue.animateTo(-100f)
            }
        }
    }
}

@Composable
fun PointerInputWithAnimateable() {
    SubtitleText(subtitle = "Drag using Pointer input with Animatable in coroutines")
    val pointerAnimatableX = remember { Animatable(0f) }

    val modifier = Modifier.pointerInput(Unit) {
        coroutineScope {
            while (true) {
                val firstDownId = awaitPointerEventScope {
                    awaitFirstDown().id
                }
                awaitPointerEventScope {
                    horizontalDrag(firstDownId, onDrag = {
                        launch {
                            pointerAnimatableX.animateTo(it.position.x)
                        }
                    })
                }
            }
        }
    }
    Card(
        backgroundColor = green200,
        modifier = modifier.size(100.dp)
            .offset(x = pointerAnimatableX.value.dp)
    ) {
    }
}

@Composable
fun DraggableCardWithAnimatable() {
    SubtitleText(subtitle = "Drag using Pointer input with Animatable in coroutines")
    Spacer(modifier = Modifier.height(100.dp))
    val pointerAnimatableX = remember { Animatable(0f) }
    val pointerAnimatableY = remember { Animatable(0f) }

   // pointerAnimatableX.updateBounds(-200f, 200f)
    //pointerAnimatableY.updateBounds(-200f, 200f)

    val modifier = Modifier.pointerInput(Unit) {
        coroutineScope {
          detectDragGestures(onDragCancel = {
              launch {
                  pointerAnimatableX.snapTo(0f)
                  pointerAnimatableY.snapTo(0f)
              }
          }) { _, dragAmount ->
              launch {
                  pointerAnimatableX.animateTo(dragAmount.x)
                  pointerAnimatableY.animateTo(dragAmount.y)
              }
          }
        }
    }
    Card(
        backgroundColor = green200,
        modifier = modifier.size(100.dp)
            .offset(x = pointerAnimatableX.value.dp, y = pointerAnimatableX.value.dp)
    ) {
    }
    Spacer(modifier = Modifier.height(100.dp))
}
//
//@Composable
//fun ColorMultistateAnimation() {
//    var colorState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    var colorFinalState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//
//    val colorAnim = transition(
//        definition = AnimationDefinitions.colorAnimDefinition,
//        initState = colorState,
//        toState = colorFinalState,
//        onStateChangeFinished = {
//            when (it) {
//                AnimationDefinitions.AnimationState.START -> {
//                    colorState = AnimationDefinitions.AnimationState.START
//                    colorFinalState = AnimationDefinitions.AnimationState.MID
//                }
//                AnimationDefinitions.AnimationState.MID -> {
//                    colorState = AnimationDefinitions.AnimationState.MID
//                    colorFinalState = AnimationDefinitions.AnimationState.END
//                }
//                AnimationDefinitions.AnimationState.END -> {
//                    colorState = AnimationDefinitions.AnimationState.END
//                    colorFinalState = AnimationDefinitions.AnimationState.START
//                }
//            }
//        }
//    )
//    val buttonColors = ButtonDefaults.buttonColors(
//        backgroundColor = colorAnim[AnimationDefinitions.colorPropKey]
//    )
//    Button(
//        modifier = Modifier.fillMaxWidth().padding(16.dp),
//        colors = buttonColors,
//        onClick = {}) {
//        Text("Color prop Animations", modifier = Modifier.padding(8.dp))
//    }
//}
//
//@Preview
//@Composable
//fun DpMultiStateAnimation() {
//    val dpStartState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val dpEndState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val dpAnim = transition(
//        definition = AnimationDefinitions.dpAnimDefinition,
//        initState = dpStartState,
//        toState = dpEndState
//    )
//
//    Row(horizontalArrangement = Arrangement.SpaceAround) {
//        Card(modifier = Modifier.preferredSize(120.dp).padding(12.dp)) {
//            Image(
//                painter = painterResource(id = R.drawable.lana),
//                contentDescription = null,
//                modifier = Modifier.height(dpAnim[AnimationDefinitions.dpPropKey])
//            )
//        }
//        Card(modifier = Modifier.preferredSize(120.dp).padding(12.dp)) {
//            Image(
//                painter = painterResource(id = R.drawable.billie),
//                contentDescription = null,
//                modifier = Modifier.height(100.dp - dpAnim[AnimationDefinitions.dpPropKey])
//            )
//        }
//    }
//}
//
//
//@Preview
//@Composable
//fun FloatMutliStateAnimation() {
//    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val floatStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val floatAnim = transition(
//        definition = AnimationDefinitions.floatAnimDefinition(0f, 100f, true),
//        initState = floatStateStart,
//        toState = floatStateFinal
//    )
//
//    Card(backgroundColor = Color.White, modifier = Modifier.preferredSize(150.dp)) {
//        Image(
//            painter = painterResource(id = R.drawable.lana),
//            contentDescription = null,
//            alpha = floatAnim[AnimationDefinitions.floatPropKey] / 100,
//        )
//    }
//
//}
//
//@Composable
//fun FloatMultiStateAnimationCircleStrokeCanvas() {
//    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val floadStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val floatAnim = transition(
//        definition = AnimationDefinitions.floatAnimDefinition(0f, 360f, true),
//        initState = floatStateStart,
//        toState = floadStateFinal
//    )
//    val stroke = Stroke(8f)
//    Canvas(modifier = Modifier.padding(16.dp).preferredSize(100.dp)) {
//        val diameter = size.minDimension
//        val radius = diameter / 2f
//        val insideRadius = radius - stroke.width
//        val topLeftOffset = Offset(
//            10f,
//            10f
//        )
//        val size = Size(insideRadius * 2, insideRadius * 2)
//        var rotationAngle = floatAnim[AnimationDefinitions.floatPropKey] - 90f
//        drawArc(
//            color = green500,
//            startAngle = rotationAngle,
//            sweepAngle = 150f,
//            topLeft = topLeftOffset,
//            size = size,
//            useCenter = false,
//            style = stroke,
//        )
//        rotationAngle += 40
//    }
//}
//
//@Preview
//@Composable
//fun FloatMultiStateAnimationCircleCanvas(color: Color = green500, radiusEnd: Float = 200f) {
//    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val floadStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val floatAnim = transition(
//        definition = AnimationDefinitions.floatAnimDefinition(0f, radiusEnd, true),
//        initState = floatStateStart,
//        toState = floadStateFinal
//    )
//
//    Canvas(modifier = Modifier.padding(16.dp)) {
//        val centerOffset = Offset(
//            10f,
//            10f
//        )
//        val radius = floatAnim[AnimationDefinitions.floatPropKey]
//        drawCircle(
//            color = color.copy(alpha = 0.8f),
//            radius = radius,
//            center = centerOffset,
//        )
//        drawCircle(
//            color = color.copy(alpha = 0.4f),
//            radius = radius / 2,
//            center = centerOffset,
//        )
//        drawCircle(
//            color = color.copy(alpha = 0.2f),
//            radius = radius / 4,
//            center = centerOffset,
//        )
//    }
//}
//
//@Composable
//fun FloatMultiStateAnimationExplode(duration: Int = 500) {
//    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val floadStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val floatAnim = transition(
//        definition = AnimationDefinitions.floatAnimDefinition(
//            0f, 2000f, false, duration
//        ),
//        initState = floatStateStart,
//        toState = floadStateFinal
//    )
//
//    Canvas(modifier = Modifier) {
//        val centerOffset = Offset(
//            10f,
//            10f
//        )
//        var radius = floatAnim[AnimationDefinitions.floatPropKey]
//        drawCircle(
//            color = green200.copy(alpha = 0.8f),
//            radius = radius,
//            center = centerOffset,
//        )
//        radius += 500
//    }
//}
//

//
//@Composable
//fun AnimatedValuesAnimations() {
//    val moveX = -1000f
//    val moveXMax = 1000f
//    TitleText(title = "Animated Value to Animations + drag")
//    val xFloat = animatedFloat(initVal = 0f)
//
//    val dragObserver = object : DragObserver {
//        override fun onStart(downPosition: Offset) {
//            xFloat.setBounds(moveX, moveXMax)
//            super.onStart(downPosition)
//        }
//
//        override fun onStop(velocity: Offset) {
//            xFloat.snapTo(moveX)
//            super.onStop(velocity)
//        }
//
//        override fun onCancel() {
//            xFloat.snapTo(moveX)
//            super.onCancel()
//        }
//
//        override fun onDrag(dragDistance: Offset): Offset {
//            xFloat.animateTo(xFloat.targetValue + dragDistance.x)
//            return super.onDrag(dragDistance)
//        }
//    }
//
//    CardElement(
//        modifier = Modifier.background(green500).preferredSize(200.dp)
//            .rawDragGestureFilter(dragObserver)
//            .offset(
//                x = Dp(xFloat.value),
//            )
//    )
//}
//
//@Composable
//fun CardElement(modifier: Modifier = Modifier) {
//    Card(
//        backgroundColor = MaterialTheme.colors.primary,
//        modifier = modifier
//    ) {}
//}
//
//
//@Composable
//fun TickerAnimation() {
//    var dpStartState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    var dpEndState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//
//    val dpAnim = transition(
//        definition = AnimationDefinitions.tickerDefinition,
//        initState = dpStartState,
//        toState = dpEndState,
//        onStateChangeFinished = { it ->
//            when (it) {
//                AnimationDefinitions.AnimationState.START -> {
//                    dpStartState = AnimationDefinitions.AnimationState.START
//                    dpEndState = AnimationDefinitions.AnimationState.END
//                }
//                AnimationDefinitions.AnimationState.MID -> {
//                    // Nothing
//                }
//                AnimationDefinitions.AnimationState.END -> {
//                    dpStartState = AnimationDefinitions.AnimationState.END
//                    dpEndState = AnimationDefinitions.AnimationState.START
//                }
//            }
//        }
//    )
//    Box(modifier = Modifier.height(50.dp).background(green700).padding(16.dp)) {
//        Text(
//            text = "15",
//            color = Color.White,
//            modifier = Modifier.offset(y = 100.dp - dpAnim[AnimationDefinitions.tickerPropKey])
//        )
//        Text(
//            text = "14",
//            color = Color.White,
//            modifier = Modifier.offset(y = 50.dp - dpAnim[AnimationDefinitions.tickerPropKey])
//        )
//        Text(
//            text = "13",
//            color = Color.White,
//            modifier = Modifier.offset(y = -dpAnim[AnimationDefinitions.tickerPropKey])
//        )
//    }
//}
//
//
//
//
