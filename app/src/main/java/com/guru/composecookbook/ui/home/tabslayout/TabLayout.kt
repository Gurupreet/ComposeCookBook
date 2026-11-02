package com.guru.composecookbook.ui.home.tabslayout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabIndicatorScope
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.instagram.components.posts.PostItem
import com.guru.composecookbook.ui.home.lists.GridListView
import com.guru.composecookbook.ui.home.lists.VerticalListView
import kotlin.random.Random

private enum class DemoTabs(val value: String) {
  APPLE("Apple"),
  GOOGLE("Google"),
  AMAZON("Amazon"),
}

@Composable
fun TabLayout() {
  val tabsName = remember { DemoTabs.values().map { it.value } }
  val selectedIndex = remember { mutableStateOf(DemoTabs.APPLE.ordinal) }
  val icons = listOf(Icons.Default.Info, Icons.Default.Person, Icons.Default.ShoppingCart)

  Column {
    // Right now Tabs by default don't have changing like viewpager but I think we can handle
    // by overriding right/left swipe on content and updating state of selectedTab or using
    // pager

    // The old M2 TabRow(backgroundColor = surface) drew a full-width 2.dp onSurface indicator and
    // a default 1.dp onSurface(12%) divider on a surface background, and dimmed the unselected
    // tab text to ContentAlpha.medium (0.6f). M3 splits TabRow into Primary/Secondary variants; the
    // Secondary variant's full-width indicator is closest to the M2 look, so use it with
    // containerColor = surface, contentColor = onSurface, a full-tab-width 2.dp onSurface indicator
    // (matching the M2 2.dp height), and the M3 default divider (a 1.dp HorizontalDivider that
    // approximates the M2 divider). The unselected tab text is dimmed to the old 0.6f.
    SecondaryTabRow(
      selectedTabIndex = selectedIndex.value,
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = MaterialTheme.colorScheme.onSurface,
      indicator = {
        TabRowDefaults.SecondaryIndicator(
          Modifier.tabIndicatorOffset(selectedIndex.value),
          height = 2.dp,
          color = MaterialTheme.colorScheme.onSurface,
        )
      },
    ) {
      tabsName.forEachIndexed { index, title ->
        val selected = index == selectedIndex.value
        Tab(
          selected = selected,
          onClick = {
            when (title) {
              DemoTabs.APPLE.value -> {
                selectedIndex.value = DemoTabs.APPLE.ordinal
              }
              DemoTabs.GOOGLE.value -> {
                selectedIndex.value = DemoTabs.GOOGLE.ordinal
              }
              DemoTabs.AMAZON.value -> {
                selectedIndex.value = DemoTabs.AMAZON.ordinal
              }
            }
          },
          text = {
            Text(
              title,
              color =
                MaterialTheme.colorScheme.onPrimaryContainer.copy(
                  alpha = if (selected) 1f else 0.6f
                ),
            )
          },
        )
      }
    }
    Surface(modifier = Modifier.weight(0.5f)) {
      when (selectedIndex.value) {
        DemoTabs.APPLE.ordinal -> {
          ScrollableListOfTabs()
        }
        DemoTabs.GOOGLE.ordinal -> {
          GridListView()
        }
        DemoTabs.AMAZON.ordinal -> {
          VerticalListView()
        }
      }
    }
  }
}

@Composable
fun ScrollableListOfTabs() {
  val tweets = remember { DemoDataProvider.tweetList.filter { it.tweetImageId > 0 } }
  val selectedIndex = remember { mutableStateOf(0) }
  Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
    // Same Secondary-variant mapping as above: the M2 ScrollableTabRow here had no indicator and
    // no divider, so the M3 scrollable Secondary variant with indicator = noIndicator and
    // divider = {} keeps the transparent, chip-only look.
    SecondaryScrollableTabRow(
      selectedTabIndex = selectedIndex.value,
      divider = {}, /* Disable the built-in divider */
      edgePadding = 16.dp,
      indicator = noIndicator,
      containerColor = Color.Transparent,
      modifier = Modifier.fillMaxWidth().padding(8.dp),
    ) {
      tweets.forEachIndexed { index, tweet ->
        Tab(selected = index == selectedIndex.value, onClick = { selectedIndex.value = index }) {
          CustomImageChip(
            text = tweet.author,
            imageId = tweet.authorImageId,
            selected = index == selectedIndex.value,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 16.dp),
          )
        }
      }
    }
    PostItem(
      post = tweets[selectedIndex.value],
      isLiked = Random.nextBoolean(),
      onLikeClicked = {},
      onCommentsClicked = {},
      onSendClicked = {},
    )
  }
}

// Inspired from jetcaster sample. I hope compose can add simple Chip UI element that can
// support images or icons with multiple states.
@Composable
private fun CustomImageChip(
  text: String,
  imageId: Int,
  selected: Boolean,
  modifier: Modifier = Modifier,
) {
  Surface(
    color =
      when {
        selected -> MaterialTheme.colorScheme.primary
        else -> Color.Transparent
      },
    contentColor =
      when {
        selected -> MaterialTheme.colorScheme.onPrimary
        else -> Color.LightGray
      },
    shape = RoundedCornerShape(16.dp),
    border =
      BorderStroke(
        width = 1.dp,
        color =
          when {
            selected -> MaterialTheme.colorScheme.primary
            else -> Color.LightGray
          },
      ),
    modifier = modifier,
  ) {
    Row(modifier = Modifier) {
      Image(
        painter = painterResource(imageId),
        contentDescription = null,
        modifier = Modifier.padding(8.dp).size(20.dp).clip(CircleShape),
      )
      Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp),
      )
    }
  }
}

private val noIndicator: @Composable TabIndicatorScope.() -> Unit = {}

@Preview
@Composable
fun PreviewTabLayout() {
  TabLayout()
}
