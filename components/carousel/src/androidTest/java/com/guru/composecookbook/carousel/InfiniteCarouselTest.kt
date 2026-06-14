package com.guru.composecookbook.carousel

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import com.guru.composecookbook.data.DemoDataProvider
import org.junit.Rule
import org.junit.Test

class InfiniteCarouselTest {

  @get:Rule val composeTestRule = createComposeRule()

  private val items = DemoDataProvider.itemList.take(3)

  // Auto advance is effectively disabled so the test controls paging deterministically.
  private fun setCarouselContent() {
    composeTestRule.setContent {
      InfiniteCarousel(items = items, autoAdvanceMillis = Long.MAX_VALUE) { item ->
        Text(text = item.title)
      }
    }
  }

  @Test
  fun firstItemIsDisplayedInitially() {
    setCarouselContent()

    composeTestRule.onNodeWithText(items[0].title).assertIsDisplayed()
  }

  @Test
  fun swipingForwardLoopsThroughItems() {
    setCarouselContent()

    composeTestRule.onNodeWithText(items[0].title).performTouchInput { swipeLeft() }
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText(items[1].title).assertIsDisplayed()
  }

  @Test
  fun swipingBackwardFromFirstItemShowsLastItem() {
    setCarouselContent()

    // Looping backwards past the first item must show the last item.
    composeTestRule.onNodeWithText(items[0].title).performTouchInput { swipeRight() }
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText(items[2].title).assertIsDisplayed()
  }
}
