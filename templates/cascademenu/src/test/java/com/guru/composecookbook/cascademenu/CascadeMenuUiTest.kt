package com.guru.composecookbook.cascademenu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the cascade menu. Opens the menu, clicks the "About" leaf item, and asserts the
 * resulting state: the itemSelected callback fires with that item's id.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
@OptIn(ExperimentalAnimationApi::class)
class CascadeMenuUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun cascadeMenu_clickLeafItemFiresItemSelected() {
    var selected: String? = null
    composeTestRule.setContent {
      Menu(isOpen = true, setIsOpen = {}, itemSelected = { selected = it })
    }
    composeTestRule.onNodeWithText("About").assertIsDisplayed()
    composeTestRule.onNodeWithText("Remove").assertIsDisplayed()
    composeTestRule.onNodeWithText("About").performClick()
    assertEquals("about", selected)
  }
}
