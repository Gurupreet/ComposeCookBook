package com.guru.composecookbook.ui.home.adaptive

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class AdaptiveUIActivityTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun everyNavigationDestinationIsLabelled() {
    composeTestRule.setContent { AdaptiveDemoScreen() }

    composeTestRule.onNodeWithText("Albums").assertIsDisplayed()
    composeTestRule.onNodeWithText("Favorites").assertIsDisplayed()
    composeTestRule.onNodeWithText("Profile").assertIsDisplayed()
  }

  @Test
  fun albumsDestinationIsShownOnLaunch() {
    composeTestRule.setContent { AdaptiveDemoScreen() }

    composeTestRule.onNodeWithText("Perfect").assertIsDisplayed()
    // Two albums in the sample data are by Ed Sheeran, so the artist alone is not unique.
    composeTestRule.onAllNodesWithText("Ed Sheeran").onFirst().assertIsDisplayed()
  }

  @Test
  fun selectingFavoritesReportsTheCurrentWindowSizeClasses() {
    composeTestRule.setContent { AdaptiveDemoScreen() }

    composeTestRule.onNodeWithText("Favorites").performClick()
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText("Width size class:", substring = true).assertIsDisplayed()
    composeTestRule.onNodeWithText("Height size class:", substring = true).assertIsDisplayed()
  }

  @Test
  fun albumListShowsSongArtistAndGenre() {
    composeTestRule.setContent { AlbumListDetail() }

    composeTestRule.onNodeWithText("Perfect").assertIsDisplayed()
    // Artist and genre both repeat across the sample data; the song title is the unique field.
    composeTestRule.onAllNodesWithText("Ed Sheeran").onFirst().assertIsDisplayed()
    composeTestRule.onAllNodesWithText("Pop").onFirst().assertIsDisplayed()
  }

  @Test
  fun tappingAnAlbumOpensItsDetailPane() {
    composeTestRule.setContent { AlbumListDetail() }

    composeTestRule.onNodeWithText("Perfect").performClick()
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText("Album by Ed Sheeran-2016").assertIsDisplayed()
  }
}
