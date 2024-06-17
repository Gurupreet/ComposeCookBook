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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.login.HorizontalDottedProgressBar
import kotlin.math.roundToInt


/**
 * A customizable swipe button that supports dragging and swiping gestures.
 *
 * @param onSwiped Callback invoked when the button is fully swiped.
 * @param modifier The modifier to be applied to the button.
 * @param swipeButtonState The current state of the swipe button.
 * @param enabled Whether the button is enabled for user interaction.
 * @param interactionSource The [MutableInteractionSource] that collects interaction events.
 * @param elevation The elevation of the button.
 * @param shape The shape of the button.
 * @param border The border stroke of the button.
 * @param colors The colors used for the button's background and content.
 * @param contentPadding The padding around the button's content.
 * @param icon The icon to display on the button.
 * @param rotateIcon Whether to rotate the icon based on drag position.
 * @param iconPadding The padding around the icon.
 * @param content The content of the button composed with [RowScope].
 */
@OptIn(ExperimentalAnimationApi::class,
ExperimentalMaterialApi::class)
@Composable
fun SwipeButton(
    onSwiped: () -> Unit,
    modifier: Modifier = Modifier,
    swipeButtonState: SwipeButtonState,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = RoundedCornerShape(20.0.dp),
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    icon: ImageVector = Icons.Default.ArrowForward,
    rotateIcon: Boolean = true,
    iconPadding: PaddingValues = PaddingValues(2.dp),
    content: @Composable RowScope.() -> Unit
) {
    val containerColor = colors.containerColor
    val dragOffset = remember { mutableStateOf(0f) }

    val collapsed = swipeButtonState == SwipeButtonState.COLLAPSED
    val swiped = swipeButtonState == SwipeButtonState.SWIPED

    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        border = border,
        shadowElevation = 0.dp,
        tonalElevation = 0.dp,
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            // Content
            val maxWidth = this.constraints.maxWidth.toFloat()

            // Display different components based on the button state
            when {
                collapsed -> {

                    // Animated IconButton when collapsed
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
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .align(
                                Alignment
                                    .Center
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Done",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                swiped -> {
                    // Display horizontal progress bar when fully swiped
                    HorizontalDottedProgressBar()
                }
                else -> {
                    // Default state with draggable IconButton
                    dragOffset.value = 0f // when button goes to inital state
                    CompositionLocalProvider() {
                        ProvideTextStyle(
                            value = MaterialTheme.typography.bodyMedium
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

            // IconButton that supports dragging for swiping action
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
                    .background(MaterialTheme.colorScheme.onPrimary, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = icon,
                        modifier = Modifier.graphicsLayer {
                            if (rotateIcon) {
                                rotationZ += dragOffset.value / 5
                            }
                        },
                        contentDescription = "Arrow",
                        tint = colors.containerColor,
                    )
                }
            }
        }
    }
}


/**
 * Represents the state of the SwipeButton.
 */
enum class SwipeButtonState {
    /**
     * Initial state of the SwipeButton.
     */
    INITIAL,

    /**
     * Swipe action completed state.
     */
    SWIPED,

    /**
     * Collapsed state of the SwipeButton.
     */
    COLLAPSED
}