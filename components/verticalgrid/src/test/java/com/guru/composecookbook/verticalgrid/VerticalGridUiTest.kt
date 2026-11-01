package com.guru.composecookbook.verticalgrid

import androidx.compose.material.Text
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

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class VerticalGridUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun verticalGrid_displaysItems() {
    composeTestRule.setContent { VerticalGrid(columns = 2) { repeat(4) { i -> Text("Item $i") } } }
    composeTestRule.onNodeWithText("Item 0").assertIsDisplayed()
    composeTestRule.onNodeWithText("Item 3").assertIsDisplayed()
  }
}
