package com.guru.composecookbook.ui.tabslayout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.ui.demoui.instagram.InstagramListItem
import com.guru.composecookbook.ui.lists.GridListView
import com.guru.composecookbook.ui.lists.VerticalListView

private enum class DemoTabs(val value: String) {
    APPLE("Apple"),
    GOOGLE("Google"),
    AMAZON("Amazon")
}

@Composable
fun TabLayout() {
    val tabsName = remember { DemoTabs.values().map { it.value } }
    var selectedIndex = remember { mutableStateOf(DemoTabs.APPLE.ordinal) }
    val icons = listOf(Icons.Default.Info, Icons.Default.Person, Icons.Default.ShoppingCart)

    Column {
        // Right now Tabs by default don't have changing like viewpager but I think we can handle
        // by overriding right/left swipe on content and updating state of selectedTab or using pager

        //Use ScrollableTabRow for list of tabs
        TabRow(selectedTabIndex = selectedIndex.value) {
            tabsName.forEachIndexed { index, title ->
                Tab(
                    selected = index == selectedIndex.value,
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
                    text = { Text(title) }
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
    var selectedIndex = remember { mutableStateOf(0) }
    ScrollableColumn {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex.value,
            divider = emptyContent(), /* Disable the built-in divider */
            edgePadding = 16.dp,
            indicator = noIndicator,
            backgroundColor = Color.Transparent,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            tweets.forEachIndexed { index, tweet ->
                Tab(
                    selected = index == selectedIndex.value,
                    onClick = {
                        selectedIndex.value = index
                    }
                ) {
                    CustomImageChip(
                        text = tweet.author,
                        imageId = tweet.authorImageId,
                        selected = index == selectedIndex.value,
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 4.dp, vertical = 16.dp)
                    )
                }
            }
        }
        InstagramListItem(post = tweets[selectedIndex.value])
    }
}

//Inspired from jetcaster sample. I hope compose can add simple Chip UI element that can
// support images or icons with multiple states.
@Composable
private fun CustomImageChip(
    text: String,
    imageId: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.primary
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> Color.LightGray
        },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> Color.LightGray
            }
        ),
        modifier = modifier
    ) {
        Row(modifier = Modifier) {
            Image(
                bitmap = imageResource(imageId),
                modifier = Modifier.padding(8.dp).preferredSize(20.dp).clip(CircleShape)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

private val noIndicator: @Composable (List<TabPosition>) -> Unit = {}


@Preview
@Composable
fun PreviewTabLayout() {
    TabLayout()
}