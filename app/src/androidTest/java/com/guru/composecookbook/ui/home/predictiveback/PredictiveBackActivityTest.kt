package com.guru.composecookbook.ui.home.predictiveback

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class PredictiveBackActivityTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun screenIsTitledAndTheDrawerStartsOpen() {
    composeTestRule.setContent { PredictiveBackDemoScreen() }

    composeTestRule.onNodeWithText("Predictive Back").assertIsDisplayed()
    composeTestRule.onNodeWithText("Mail").assertIsDisplayed()
    composeTestRule.onNodeWithText("Inbox").assertIsDisplayed()
    composeTestRule.onNodeWithText("Trash").assertIsDisplayed()
  }

  @Test
  fun openDrawerExplainsTheBackGestureAffordance() {
    composeTestRule.setContent { PredictiveBackDemoScreen() }

    composeTestRule.onNodeWithText("Content area").assertIsDisplayed()
    composeTestRule
      .onNodeWithText("the drawer follows your gesture", substring = true)
      .assertIsDisplayed()
  }

  @Test
  fun togglingTheDrawerClosedHidesItAndOffersToReopen() {
    composeTestRule.setContent { PredictiveBackDemoScreen() }

    composeTestRule.onNodeWithContentDescription("Toggle drawer").performClick()
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText("Mail").assertDoesNotExist()
    composeTestRule.onNodeWithText("Inbox").assertDoesNotExist()
    composeTestRule.onNodeWithText("Reopen drawer").assertIsDisplayed()
    composeTestRule.onNodeWithText("Drawer closed.", substring = true).assertIsDisplayed()
  }

  @Test
  fun reopenButtonBringsTheDrawerBack() {
    composeTestRule.setContent { PredictiveBackDemoScreen() }

    composeTestRule.onNodeWithContentDescription("Toggle drawer").performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithText("Reopen drawer").performClick()
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText("Mail").assertIsDisplayed()
    composeTestRule.onNodeWithText("Reopen drawer").assertDoesNotExist()
  }
}
