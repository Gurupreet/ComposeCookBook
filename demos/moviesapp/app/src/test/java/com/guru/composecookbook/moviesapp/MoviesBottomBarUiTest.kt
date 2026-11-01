package com.guru.composecookbook.moviesapp.ui.home

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
class MoviesBottomBarUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun moviesBottomBar_clickSwitchesTab() {
    val navType = mutableStateOf(MovieNavType.SHOWING)
    composeTestRule.setContent { MoviesBottomBar(navType) }
    composeTestRule.onNodeWithText("Showing").assertIsDisplayed()
    composeTestRule.onNodeWithText("Trending").assertIsDisplayed()
    composeTestRule.onNodeWithText("Watchlist").assertIsDisplayed()
    composeTestRule.onNodeWithTag("movies_nav_trending").performClick()
    assertEquals(MovieNavType.TRENDING, navType.value)
  }
}
