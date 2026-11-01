package com.guru.composecookbook.instagram

import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.instagram.components.InstagramHome
import com.guru.composecookbook.theme.ComposeCookBookTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the Instagram demo. Asserts the home screen renders (the messaging action is
 * present), then taps the messaging icon and asserts the resulting state: onMessagingClicked fires.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class InstagramUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun instagram_messagingIconFiresCallback() {
    var messagingClicked = false
    composeTestRule.setContent {
      ComposeCookBookTheme(darkTheme = false) {
        InstagramHome(
          posts = DemoDataProvider.tweetList.filter { it.tweetImageId != 0 },
          profiles = DemoDataProvider.tweetList,
          onLikeClicked = {},
          onCommentsClicked = {},
          onSendClicked = {},
          onProfileClicked = {},
          onMessagingClicked = { messagingClicked = true },
        )
      }
    }
    // The messaging action is present on the top app bar.
    composeTestRule.onNodeWithContentDescription("Go to messaging screen").assertExists()

    composeTestRule.onNodeWithContentDescription("Go to messaging screen").performClick()
    assertTrue("tapping the messaging icon should fire onMessagingClicked", messagingClicked)
  }
}
