package com.guru.composecookbook.ui.advancelists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.advancelists.AdvanceListsActivity.Companion.tabs
import com.guru.composecookbook.ui.carousel.Pager
import com.guru.composecookbook.ui.carousel.PagerState
import com.guru.composecookbook.ui.components.DiagonalCardShimmer
import com.guru.composecookbook.ui.dynamic.DynamicUIActivity

class AdvanceListsActivity : AppCompatActivity() {

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookTheme(isDarkTheme) {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Advance Lists(In Progress)") },
                            elevation = 0.dp,
                            navigationIcon = {
                                IconButton(onClick = { onBackPressed() }) {
                                    Icon(Icons.Filled.ArrowBack)
                                }
                            }
                        )
                    }
                ) {
                    AdvanceListContent()
                }
            }
        }
    }

    companion object {
        val tabs = listOf("Shimmers", "Animated Lists", "Swipeable Lists", "List Shimmer", "Diagonal Shimmer")
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, AdvanceListsActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun AdvanceListContent() {
    var selectedIndex by remember { mutableStateOf(0) }
    val pagerState: PagerState = run {
        val clock = AnimationClockAmbient.current
        remember(clock) {
            PagerState(clock, 0, 0, tabs.size - 1)
        }
    }
    Column {
        ScrollableTabRow(selectedTabIndex = selectedIndex, edgePadding = 12.dp) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = index == selectedIndex,
                    onClick = {
                        selectedIndex = tabs.indexOf(title)
                        pagerState.currentPage = tabs.indexOf(title)
                    },
                    text = { Text(title) }
                )
            }
        }
        Pager(state = pagerState, modifier = Modifier.weight(1f)) {
            selectedIndex = pagerState.currentPage
            when (page) {
                0 -> ShimmerList()
                1 -> AnimatedLists()
                2 -> SwipeableLists()
                3 -> LoadingListShimmer()
                4 -> DiagonalCardShimmer(260.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview10() {
    ComposeCookBookTheme {
        ShimmerList()
    }
}