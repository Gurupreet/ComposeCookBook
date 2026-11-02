package com.guru.composecookbook.datingapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
 * UI test for the dating app bottom bar. The app bar title reflects the selected nav tab
 * ("Discover" for the initial PEOPLES tab); tapping the "Chats" bar item (testTag
 * "dating_nav_chats", added in main code) switches the resulting navType state to CHATS. The bar
 * and app bar are stacked in a Column so the bar item is not covered and is clickable.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
@OptIn(ExperimentalMaterial3Api::class)
class DatingAppbarUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun datingBottomBar_switchesNavType() {
    val navType = mutableStateOf(DatingNavType.PEOPLES)
    composeTestRule.setContent {
      Column {
        DatingHomeAppbar(navType)
        DatingBottomBar(navType)
      }
    }
    // Initial tab (PEOPLES) shows the "Discover" title.
    composeTestRule.onNodeWithText("Discover").assertIsDisplayed()

    composeTestRule.onNodeWithTag("dating_nav_chats").performClick()
    assertEquals(DatingNavType.CHATS, navType.value)
  }
}
