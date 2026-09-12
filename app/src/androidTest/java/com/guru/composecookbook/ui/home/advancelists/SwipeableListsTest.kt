package com.guru.composecookbook.ui.home.advancelists

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import org.junit.Rule
import org.junit.Test

class SwipeableListsTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun albumsAreListedOnFirstComposition() {
    composeTestRule.setContent { SwipeableLists() }

    composeTestRule.onNodeWithText("Perfect").assertIsDisplayed()
    composeTestRule.onNodeWithText("Havana").assertIsDisplayed()
    // Two albums in the sample data are by Ed Sheeran, so the artist alone is not unique.
    composeTestRule.onAllNodesWithText("Ed Sheeran").onFirst().assertIsDisplayed()
  }

  @Test
  fun everyRowExposesItsOverflowButtonToAccessibilityServices() {
    composeTestRule.setContent { SwipeableLists() }

    composeTestRule.onAllNodesWithContentDescription("More options").onFirst().assertIsDisplayed()
  }

  @Test
  fun swipingEndToStartDeletesTheRowAndOffersUndo() {
    composeTestRule.setContent { SwipeableLists() }

    // The swipe must be dispatched to the row, not to the song title: the title node is only a
    // few pixels wide, well short of the box's 50% positional threshold.
    composeTestRule.onNodeWithText("Perfect").onParent().performTouchInput { swipeLeft() }
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText("Perfect").assertDoesNotExist()
    composeTestRule.onNodeWithText("Perfect deleted").assertIsDisplayed()
    composeTestRule.onNodeWithText("Undo").assertIsDisplayed()
  }

  @Test
  fun swipingStartToEndArchivesTheRowAndOffersUndo() {
    composeTestRule.setContent { SwipeableLists() }

    composeTestRule.onNodeWithText("Perfect").onParent().performTouchInput { swipeRight() }
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText("Perfect").assertDoesNotExist()
    composeTestRule.onNodeWithText("Perfect archived").assertIsDisplayed()
  }

  @Test
  fun undoRestoresASwipedAwayRow() {
    composeTestRule.setContent { SwipeableLists() }

    composeTestRule.onNodeWithText("Perfect").onParent().performTouchInput { swipeLeft() }
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithText("Undo").performClick()
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText("Perfect").assertIsDisplayed()
  }
}
