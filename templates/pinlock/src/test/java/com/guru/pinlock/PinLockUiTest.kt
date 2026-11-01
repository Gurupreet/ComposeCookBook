package com.guru.pinlock

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the pin-lock template. Taps the four wrong PIN digits (1, 2, 3, 4) and asserts the
 * resulting state: the "Wrong pin, Please retry!" error is displayed. A larger screen qualifier is
 * used so the bottom digit rows are laid out within the window.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class PinLockUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun pinLock_wrongPinShowsError() {
    composeTestRule.setContent {
      ComposeCookBookMaterial3Theme(darkTheme = false) { PinLockView() }
    }
    composeTestRule.onNodeWithText("Enter pin to unlock").assertIsDisplayed()

    composeTestRule.onNodeWithText("1").performClick()
    composeTestRule.onNodeWithText("2").performClick()
    composeTestRule.onNodeWithText("3").performClick()
    composeTestRule.onNodeWithText("4").performClick()

    composeTestRule.onNodeWithText("Wrong pin, Please retry!").assertIsDisplayed()
  }
}
