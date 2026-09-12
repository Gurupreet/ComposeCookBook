package com.guru.composecookbook.ui.home.sharedelement

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class SharedElementActivityTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun albumListIsShownBeforeAnySelection() {
    composeTestRule.setContent { SharedElementDemo() }

    composeTestRule.onNodeWithText("Perfect").assertIsDisplayed()
    composeTestRule.onNodeWithText("Havana").assertIsDisplayed()
    // Two albums in the sample data are by Ed Sheeran, so the artist alone is not unique.
    composeTestRule.onAllNodesWithText("Ed Sheeran").onFirst().assertIsDisplayed()
    composeTestRule.onNodeWithText("Tap anywhere to go back").assertDoesNotExist()
  }

  @Test
  fun albumArtIsDescribedForAccessibility() {
    composeTestRule.setContent { SharedElementDemo() }

    composeTestRule.onAllNodesWithContentDescription("Perfect").onFirst().assertExists()
  }

  @Test
  fun tappingAnAlbumTransitionsToItsDetail() {
    composeTestRule.setContent { SharedElementDemo() }

    composeTestRule.onNodeWithText("Perfect").performClick()
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText("Album by Ed Sheeran-2016").assertIsDisplayed()
    composeTestRule.onNodeWithText("Tap anywhere to go back").assertIsDisplayed()
    // The detail screen replaces the list, so sibling rows are gone.
    composeTestRule.onNodeWithText("Havana").assertDoesNotExist()
  }

  @Test
  fun tappingTheDetailReturnsToTheList() {
    composeTestRule.setContent { SharedElementDemo() }

    composeTestRule.onNodeWithText("Perfect").performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithText("Tap anywhere to go back").performClick()
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText("Havana").assertIsDisplayed()
    composeTestRule.onNodeWithText("Tap anywhere to go back").assertDoesNotExist()
  }
}
