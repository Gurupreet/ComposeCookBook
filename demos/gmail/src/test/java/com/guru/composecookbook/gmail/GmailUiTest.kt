package com.guru.composecookbook.gmail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.gmail.ui.home.GmailListItem
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the Gmail demo. Asserts the mail list item renders (sender + date), then taps the
 * item and asserts the resulting state: clickListener fires with the tapped item.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class GmailUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun gmailListItem_tappingFiresClickListener() {
    var clickedItem: com.guru.composecookbook.data.model.Tweet? = null
    composeTestRule.setContent {
      ComposeCookBookMaterial3Theme(darkTheme = false) {
        GmailListItem(
          item = DemoDataProvider.tweet,
          clickListener = { clickedItem = it },
        )
      }
    }
    composeTestRule.onNodeWithText("The Verge").assertIsDisplayed()
    composeTestRule.onNodeWithText("5 sept").assertIsDisplayed()

    composeTestRule.onNodeWithText("The Verge").performClick()
    assertSame(DemoDataProvider.tweet, clickedItem)
  }
}
