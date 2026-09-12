package com.guru.composecookbook.ui.home.pullrefreshdemos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PullRefreshListTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun firstAlbumIsDisplayedInitially() {
    composeTestRule.setContent { PullRefreshList(onPullRefresh = {}) }

    composeTestRule.onNodeWithText("Perfect").assertIsDisplayed()
    // Two albums in the sample data are by Ed Sheeran, so this row text is not unique.
    composeTestRule
      .onAllNodesWithText("Ed Sheeran, Album by Ed Sheeran-2016")
      .onFirst()
      .assertIsDisplayed()
  }

  @Test
  fun refreshedAlbumIsAbsentBeforeAnyRefresh() {
    composeTestRule.setContent { PullRefreshList(onPullRefresh = {}) }

    // AlbumsDataProvider.album is only prepended by a refresh, so it must not be there yet.
    // Match on the artist/description line: it is the one string unique to that album.
    composeTestRule.onNodeWithText("Adele, Album by Adele-2016").assertDoesNotExist()
  }

  @Test
  fun pullingDownInvokesTheRefreshCallback() {
    var invocations = 0
    composeTestRule.setContent { PullRefreshList(onPullRefresh = { invocations++ }) }

    // The gesture has to travel far enough to cross PullToRefreshBox's threshold, so it is
    // dispatched to the whole screen rather than to a single row.
    composeTestRule.onRoot().performTouchInput { swipeDown() }
    composeTestRule.waitForIdle()

    assertEquals(1, invocations)
  }
}
