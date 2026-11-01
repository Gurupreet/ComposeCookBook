package com.guru.composecookbook.colorpicker

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the color picker module. Taps the gradient slider (testTag "colorpicker_slider",
 * added in main code) and asserts the resulting state: the onColorSelected callback fires with a
 * selected color, and the heading remains on screen.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class ColorPickerUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun colorPicker_tapSliderSelectsColor() {
    var selected: Color? = null
    composeTestRule.setContent { ColorPicker { selected = it } }
    composeTestRule.onNodeWithText("Color picker with draggable").assertIsDisplayed()
    composeTestRule.onNodeWithTag("colorpicker_slider").performClick()
    assertNotNull("tapping the slider should invoke onColorSelected", selected)
    assertNotEquals("tapping the slider should change the active color", Color.Red, selected)
  }
}
