package com.guru.composecookbook.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the onboarding screen. Clicking "Next" advances the pager; on the last page the
 * button label changes to "Let's Begin" (the resulting state assertion).
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class OnboardingUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun onboarding_nextAdvancesToLastPage() {
    composeTestRule.setContent { OnBoardingScreen(onSkip = {}) }
    composeTestRule.onNodeWithText("Skip").assertIsDisplayed()
    composeTestRule.onNodeWithText("Next").assertIsDisplayed()
    // Two pages forward reaches the last of the three onboarding pages.
    composeTestRule.onNodeWithText("Next").performClick()
    composeTestRule.onNodeWithText("Next").performClick()
    composeTestRule.onNodeWithText("Let's Begin").assertIsDisplayed()
  }
}
