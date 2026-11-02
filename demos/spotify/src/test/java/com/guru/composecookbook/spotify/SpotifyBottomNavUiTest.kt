package com.guru.composecookbook.spotify.ui.home

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

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class SpotifyBottomNavUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun spotifyBottomNavigation_clickSwitchesTab() {
    val state = mutableStateOf(SpotifyNavType.HOME)
    composeTestRule.setContent { SpotifyBottomNavigation(state) }
    composeTestRule.onNodeWithText("Home").assertIsDisplayed()
    composeTestRule.onNodeWithText("Search").assertIsDisplayed()
    composeTestRule.onNodeWithText("Your Library").assertIsDisplayed()
    composeTestRule.onNodeWithTag("spotify_nav_search").performClick()
    assertEquals(SpotifyNavType.SEARCH, state.value)
  }
}
