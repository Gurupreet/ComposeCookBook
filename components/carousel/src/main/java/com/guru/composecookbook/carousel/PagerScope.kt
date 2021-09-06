package com.guru.composecookbook.carousel

/**
 * Scope for [Pager] content.
 */
class PagerScope(
    private val state: PagerState,
    val commingPage: Int
) {
    /**
     * Returns the current selected page
     */
    val currentPage: Int
        get() = state.currentPage

    /**
     * Returns the current selected page offset
     */
    val currentPageOffset: Float
        get() = state.currentPageOffset

    /**
     * Returns the current selection state
     */
    val selectionState: SelectionState
        get() = state.selectionState

    /**
     * Modifier which scales pager items according to their offset position. Similar in effect
     * to a carousel.
     */
//    fun Modifier.scalePagerItems(
//        unselectedScale: Float
//    ): Modifier = Modifier.drawWithContent {
//        if (selectionState == PagerState.SelectionState.Selected) {
//            // If the pager is 'selected', it's stationary so we use a simple if check
//            if (page != currentPage) {
//                scale(
//                    scaleX = unselectedScale,
//                    scaleY = unselectedScale,
//                    pivot = center,
//                ) {
//                    this@drawWithContent.drawContent()
//                }
//            } else {
//                drawContent()
//            }
//        } else {
//            // Otherwise the pager is being scrolled, so we need to look at the swipe progress
//            // and interpolate between the sizes
//            val offsetForPage = page - currentPage + currentPageOffset
//
//            val scale = if (offsetForPage < 0) {
//                // If the page is to the left of the current page, we scale from min -> 1f
//                lerp(
//                    start = unselectedScale,
//                    stop = 1f,
//                    fraction = (1f + offsetForPage).coerceIn(0f, 1f)
//                )
//            } else {
//                // If the page is to the right of the current page, we scale from 1f -> min
//                lerp(
//                    start = 1f,
//                    stop = unselectedScale,
//                    fraction = offsetForPage.coerceIn(0f, 1f)
//                )
//            }
//            scale(scale, scale, center) {
//                this@drawWithContent.drawContent()
//            }
//        }
//    }
}