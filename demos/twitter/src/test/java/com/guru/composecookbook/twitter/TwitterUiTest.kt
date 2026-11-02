package com.guru.composecookbook.twitter

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.twitter.components.tweets.TweetItem
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the Twitter demo. Asserts the tweet renders (author name), then taps the "like"
 * counter and asserts the resulting state: onLikesClick fires.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class TwitterUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun tweet_likeCounterFiresCallback() {
    var likesClicked = false
    composeTestRule.setContent {
      ComposeCookBookTheme(darkTheme = false) {
        TweetItem(
          tweet = DemoDataProvider.tweet,
          onMessagesClick = {},
          onRetweetClick = {},
          onLikesClick = { likesClicked = true },
          onShareClick = {},
        )
      }
    }
    composeTestRule.onNodeWithText("The Verge").assertIsDisplayed()

    composeTestRule.onNodeWithContentDescription("100 likes. Like this tweet too!").performClick()
    assertTrue("tapping the like counter should fire onLikesClick", likesClicked)
  }
}
