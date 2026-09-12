package com.guru.composecookbook.carousel

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
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

    // The gesture must span the pager, not the narrow Text node inside the page.
    composeTestRule.onRoot().performTouchInput { swipeLeft() }
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText(items[1].title).assertIsDisplayed()
  }

  @Test
  fun swipingBackwardFromFirstItemShowsLastItem() {
    setCarouselContent()

    // Looping backwards past the first item must show the last item.
    composeTestRule.onRoot().performTouchInput { swipeRight() }
    composeTestRule.waitForIdle()

    composeTestRule.onNodeWithText(items[2].title).assertIsDisplayed()
  }

  @Test
  fun emptyListRendersNothingInsteadOfCrashing() {
    // Composing at all is the real assertion: without the guard the page-index modulo
    // divides by zero and this call throws before any assertion is reached.
    composeTestRule.setContent {
      InfiniteCarousel(items = emptyList(), autoAdvanceMillis = Long.MAX_VALUE) {
        Text(text = PAGE_CONTENT)
      }
    }

    composeTestRule.onNodeWithText(PAGE_CONTENT).assertDoesNotExist()
  }

  private companion object {
    const val PAGE_CONTENT = "page content"
  }
}
