package com.guru.composecookbook.ui.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText

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
fun SimpleColorStateAnimation() {
    SubtitleText(subtitle = "Animate color")
    val enabled = remember { mutableStateOf(true) }
    val animatedColor = animateColorAsState(
        targetValue =
        if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    )

    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = animatedColor.value
    )

    Button(
        onClick = { enabled.value = !enabled.value },
        colors = buttonColors,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
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
        targetValue = if (enabled) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    )
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
            .width(animatedWidthState.value),
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
        targetValue = if (enabled) Offset(0f, 0f) else Offset(50f, 40f)
    )
    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Image(
            painterResource(id = R.drawable.p1),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .offset(x = Dp(animatedOffset.x), y = Dp(animatedOffset.y))
                .clickable { enabled = !enabled }
        )
        Image(
            painterResource(id = R.drawable.p2),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
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
            convertFromVector = { CustomAnimationState(it.v1.dp, it.v2) }
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

    Spacer(modifier = Modifier.padding(30.dp))
    var draw2 by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.adele21),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw2) 30f else 5f).value,
                    translationX = animateFloatAsState(if (draw2) 320f else 0f).value,
                    translationY = 0f,
                )
                .clickable(onClick = { draw2 = !draw2 })
        )
        Image(
            painter = painterResource(id = R.drawable.dualipa),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw2) 30f else 10f).value,
                    translationX = animateFloatAsState(if (draw2) -320f else 0f).value,
                    translationY = animateFloatAsState(if (draw2) 0f else 30f).value
                )
                .clickable(onClick = { draw2 = !draw2 })
        )
        Image(
            painter = painterResource(id = R.drawable.edsheeran),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw2) 30f else 5f).value,
                    translationY = animateFloatAsState(if (draw2) 0f else 50f).value
                )
                .clickable(onClick = { draw2 = !draw2 })
        )
    }
    Spacer(modifier = Modifier.padding(30.dp))
    var draw3 by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.wolves),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw3) 30f else 5f).value,
                    translationX = animateFloatAsState(if (draw3) 320f else 0f).value,
                    rotationY = animateFloatAsState(if (draw3) 45f else 0f).value,
                    translationY = 0f
                )
                .clickable(onClick = { draw3 = !draw3 })
        )
        Image(
            painter = painterResource(id = R.drawable.sam),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw3) 30f else 10f).value,
                    translationX = animateFloatAsState(if (draw3) -320f else 0f).value,
                    rotationY = animateFloatAsState(if (draw3) 45f else 0f).value,
                    translationY = animateFloatAsState(if (draw3) 0f else 30f).value
                )
                .clickable(onClick = { draw3 = !draw3 })
        )
        Image(
            painter = painterResource(id = R.drawable.billie),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw3) 30f else 5f).value,
                    translationY = animateFloatAsState(if (draw3) 0f else 50f).value,
                    rotationY = animateFloatAsState(if (draw3) 45f else 0f).value
                )
                .clickable(onClick = { draw3 = !draw3 })
        )
    }
    Spacer(modifier = Modifier.padding(30.dp))
    var draw4 by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.imagindragon),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw4) 30f else 5f).value,
                    translationX = animateFloatAsState(if (draw4) 320f else 0f).value,
                    rotationZ = animateFloatAsState(if (draw4) 45f else 0f).value,
                    translationY = 0f
                )
                .clickable(onClick = { draw4 = !draw4 })
        )
        Image(
            painter = painterResource(id = R.drawable.khalid),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw4) 30f else 10f).value,
                    translationX = animateFloatAsState(if (draw4) -320f else 0f).value,
                    rotationZ = animateFloatAsState(if (draw4) 45f else 0f).value,
                    translationY = animateFloatAsState(if (draw4) 0f else 30f).value
                )
                .clickable(onClick = { draw4 = !draw4 })
        )
        Image(
            painter = painterResource(id = R.drawable.camelia),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw4) 30f else 5f).value,
                    translationY = animateFloatAsState(if (draw4) 0f else 50f).value,
                    rotationZ = animateFloatAsState(if (draw4) 45f else 0f).value,
                )
                .clickable(onClick = { draw4 = !draw4 })
        )
        Spacer(modifier = Modifier.padding(60.dp))
    }
}