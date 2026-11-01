package com.guru.composecookbook.canvas

import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the canvas module. The composable runs an infinite canvas animation
 * (rememberInfiniteTransition + infiniteRepeatable), so the test clock is paused
 * (mainClock.autoAdvance = false) and advanced a single frame instead of letting the infinite loop
 * spin on the clock. The Canvas node is asserted by its testTag (added to the Canvas in main code)
 * rather than an always-true onRoot() check. The Canvas has no intrinsic size (its drawCircle
 * content does not size it), so existence — not display — is asserted.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class CanvasUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun canvas_displaysAnimatedCircle() {
    composeTestRule.mainClock.autoAdvance = false
    composeTestRule.setContent { MultiStateAnimationCircleFilledCanvas() }
    // Advance one frame so the infinite transition renders a frame on the canvas.
    composeTestRule.mainClock.advanceTimeByFrame()
    composeTestRule.onNodeWithTag("canvas_filled_circle").assertExists()
  }
}
