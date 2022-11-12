package com.guru.composecookbook.carousel

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


private val Measurable.page: Int
    get() = (parentData as? PageData)?.page ?: error("no PageData for measurable $this")

@Composable
fun Pager(
    modifier: Modifier = Modifier,
    state: PagerState,
    orientation: Orientation = Orientation.Horizontal,
    offscreenLimit: Int = 2,
    content: @Composable PagerScope.() -> Unit
) {
    var pageSize by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Layout(
        content = {
            state.PrepareContent(offscreenLimit = offscreenLimit) {
                content.invoke(this)
            }
        },
        modifier = modifier.draggable(
            orientation = orientation,
            onDragStarted = {
                state.selectionState = SelectionState.Undecided
            },
            onDragStopped = { velocity ->
                coroutineScope.launch {
                    // Velocity is in pixels per second, but we deal in percentage offsets, so we
                    // need to scale the velocity to match
                    state.fling(velocity / pageSize)
                }
            },
            state = rememberDraggableState { deltaInPx ->
                coroutineScope.calculateNewPosition(deltaInPx, state, pageSize, offscreenLimit)
            },
        )
    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val currentPage = state.currentPage
            val offset = state.currentPageOffset
            val childConstraints = constraints.copy(minWidth = 0, minHeight = 0)

            measurables
                .map {
                    it.measure(childConstraints) to it.page
                }
                .forEach { (placeable, page) ->
                    // TODO: current this centers each page. We should investigate reading
                    //  gravity modifiers on the child, or maybe as a param to Pager.
                    val xCenterOffset = (constraints.maxWidth - placeable.width) / 2
                    val yCenterOffset = (constraints.maxHeight - placeable.height) / 2

                    if (currentPage == page) {
                        pageSize = if (orientation == Orientation.Horizontal) {
                            placeable.width
                        } else {
                            placeable.height
                        }
                    }
                    if (orientation == Orientation.Horizontal) {
                        placeable.placeRelative(
                            x = xCenterOffset + ((page - (currentPage - offset)) * placeable.width).roundToInt(),
                            y = yCenterOffset
                        )
                    } else {
                        placeable.placeRelative(
                            x = xCenterOffset,
                            y = yCenterOffset + ((page - (currentPage - offset)) * placeable.height).roundToInt()
                        )
                    }
                }
        }
    }
}

@Composable
private fun PagerState.PrepareContent(
    offscreenLimit: Int,
    content: @Composable PagerScope.() -> Unit
) {
    val minPage = (currentPage - offscreenLimit).coerceAtLeast(minPage)
    val maxPage = (currentPage + offscreenLimit).coerceAtMost(maxPage)

    for (page in minPage..maxPage) {
        val pageData = PageData(page)
        val scope = PagerScope(this, page)
        key(pageData) {
            Column(modifier = pageData) {
                scope.content()
            }
        }
    }
}

private fun CoroutineScope.calculateNewPosition(
    delta: Float,
    state: PagerState,
    pageSize: Int,
    offscreenLimit: Int
) {
    launch {
        with(state) {
            val pos = pageSize * currentPageOffset
            val max = if (currentPage == minPage) 0 else pageSize * offscreenLimit
            val min = if (currentPage == maxPage) 0 else -pageSize * offscreenLimit
            val newPos = (pos + delta).coerceIn(min.toFloat(), max.toFloat())
            snapToOffset(newPos / pageSize)
        }
    }
}