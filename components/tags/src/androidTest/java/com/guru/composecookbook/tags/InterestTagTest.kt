package com.guru.composecookbook.tags

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class InterestTagTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun interestTagDisplaysItsText() {
    composeTestRule.setContent { InterestTag(text = "Jetpack Compose") }

    composeTestRule.onNodeWithText("Jetpack Compose").assertIsDisplayed()
  }

  @Test
  fun interestTagIsClickable() {
    composeTestRule.setContent { InterestTag(text = "Clickable tag") }

    composeTestRule.onNodeWithText("Clickable tag").assertHasClickAction()
  }

  @Test
  fun interestTagInvokesOnClick() {
    var clicked = false
    composeTestRule.setContent { InterestTag(text = "Tap me", onClick = { clicked = true }) }

    composeTestRule.onNodeWithText("Tap me").performClick()

    assertTrue(clicked)
  }
}
