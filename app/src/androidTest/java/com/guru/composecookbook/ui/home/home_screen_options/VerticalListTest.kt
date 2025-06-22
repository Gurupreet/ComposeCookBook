package com.guru.composecookbook.ui.home.home_screen_options

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.MainActivity
import com.guru.composecookbook.ui.utils.TestTags
import org.junit.Rule
import org.junit.Test

/**
 * UI test for Vertical List page.
 *
 * @author https://github.com/iamjosephmj
 */
class VerticalListTest {

  @get:Rule val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun homeListOfEntriesMustBeVisible() {
    composeAndroidTestRule.apply { onNodeWithTag(TestTags.HOME_SCREEN_LIST).assertExists() }
  }

  @Test
  fun assertIfVerticalListButtonVisible() {
    composeAndroidTestRule.apply { onNodeWithTag("button-Vertical ListView").assertIsDisplayed() }
  }

  @Test
  fun assertIfVerticalListIsVisible() {
    composeAndroidTestRule.apply {
      onNodeWithTag("button-Vertical ListView").assertIsDisplayed().performClick()

      onNodeWithTag("${TestTags.HOME_SCREEN_LIST_ITEM}-1").assertIsDisplayed()
    }
  }
}
