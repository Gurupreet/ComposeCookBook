package com.guru.composecookbook.carousel

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lens
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.guru.composecookbook.data.model.Item
import kotlinx.coroutines.delay

/**
 * An infinitely looping carousel built on top of foundation's [HorizontalPager].
 *
 * The pager is given a very large virtual page count and starts in the middle, so the user can
 * swipe endlessly in both directions; the virtual page index is mapped back onto the real items
 * with a modulo. While idle it auto-advances every [autoAdvanceMillis]; auto-advance pauses while
 * the user is dragging.
 */
@Composable
fun InfiniteCarousel(
  items: List<Item>,
  modifier: Modifier = Modifier,
  autoAdvanceMillis: Long = 3000L,
  content: @Composable (Item) -> Unit,
) {
  // Start in the middle of the virtual range, aligned to the first real item.
  val virtualCount = Int.MAX_VALUE
  val startPage = remember(items) { virtualCount / 2 - ((virtualCount / 2) % items.size) }
  val pagerState = rememberPagerState(initialPage = startPage) { virtualCount }
  val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

  // Auto advance while the user is not interacting with the pager.
  LaunchedEffect(isDragged, items) {
    if (!isDragged) {
      while (true) {
        delay(autoAdvanceMillis)
        pagerState.animateScrollToPage(pagerState.currentPage + 1)
      }
    }
  }

  Column(modifier = modifier) {
    HorizontalPager(state = pagerState) { page -> content(items[page % items.size]) }
    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
      val selectedIndex = pagerState.currentPage % items.size
      items.forEachIndexed { index, _ ->
        CarouselDot(
          selected = index == selectedIndex,
          color = MaterialTheme.colorScheme.primary,
          icon = Icons.Filled.Lens,
        )
      }
    }
  }
}
