package com.guru.composecookbook.charts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the charts module. [Charts] embeds an infinite animation (rememberInfiniteTransition
 * on the chart bars), so the test clock is paused (mainClock.autoAdvance = false) and advanced a
 * single frame to render the screen deterministically instead of letting the infinite loop spin on
 * the clock.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class ChartsUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun charts_displaysHeading() {
    composeTestRule.mainClock.autoAdvance = false
    composeTestRule.setContent { Charts() }
    composeTestRule.mainClock.advanceTimeByFrame()
    composeTestRule.onNodeWithText("Compose charts").assertIsDisplayed()
  }
}
