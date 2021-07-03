package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.login.HorizontalDottedProgressBar
import kotlin.math.roundToInt

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SwipeButton(
    onSwiped: () -> Unit,
    modifier: Modifier = Modifier,
    swipeButtonState: SwipeButtonState,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    icon: ImageVector = Icons.Default.ArrowForward,
    rotateIcon: Boolean = true,
    iconPadding: PaddingValues = PaddingValues(2.dp),
    content: @Composable RowScope.() -> Unit
) {
    val contentColor by colors.contentColor(enabled)
    val dragOffset = remember { mutableStateOf(0f) }

    val collapsed = swipeButtonState == SwipeButtonState.COLLAPSED
    val swiped = swipeButtonState == SwipeButtonState.SWIPED

    Surface(
        modifier = modifier,
        shape = shape,
        color = colors.backgroundColor(enabled).value,
        contentColor = contentColor.copy(alpha = 1f),
        border = border,
        elevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp,
        onClick = {},
        enabled = enabled,
        role = Role.Button,
        interactionSource = interactionSource,
        indication = rememberRipple()
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            // Content
            val maxWidth = this.constraints.maxWidth.toFloat()

            when {
                collapsed -> {
                    val animatedProgress = remember { Animatable(initialValue = 0f) }
                    LaunchedEffect(Unit) {
                        animatedProgress.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(600)
                        )
                    }
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .scale(animatedProgress.value)
                            .padding(iconPadding)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onPrimary)
                            .align(
                                Alignment
                                    .Center
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Done",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
                swiped -> {
                    HorizontalDottedProgressBar()
                }
                else -> {
                    dragOffset.value = 0f // when button goes to inital state
                    CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
                        ProvideTextStyle(
                            value = MaterialTheme.typography.button
                        ) {
                            Row(
                                Modifier
                                    .fillMaxSize()
                                    .padding(contentPadding),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                content = content
                            )
                        }
                    }
                }
            }
            // Swipe Component
            AnimatedVisibility(visible = !swiped) {
                IconButton(onClick = { }, enabled = enabled, modifier = Modifier
                    .padding(iconPadding)
                    .align(Alignment.CenterStart)
                    .offset { IntOffset(dragOffset.value.roundToInt(), 0) }
                    .draggable(
                        enabled = enabled,
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            val newValue = dragOffset.value + delta
                            dragOffset.value = newValue.coerceIn(0f, maxWidth)
                        },
                        onDragStopped = {
                            if (dragOffset.value > maxWidth * 2 / 3) {
                                dragOffset.value = maxWidth
                                onSwiped.invoke()
                            } else {
                                dragOffset.value = 0f
                            }
                        }
                    )
                    .background(MaterialTheme.colors.onPrimary, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = icon,
                        modifier = Modifier.graphicsLayer {
                            if (rotateIcon) {
                                rotationZ += dragOffset.value / 5
                            }
                        },
                        contentDescription = "Arrow",
                        tint = colors.backgroundColor(enabled).value,
                    )
                }
            }
        }
    }
}

enum class SwipeButtonState {
    INITIAL, SWIPED, COLLAPSED
}