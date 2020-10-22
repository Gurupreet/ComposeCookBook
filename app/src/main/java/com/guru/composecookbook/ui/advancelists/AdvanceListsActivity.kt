package com.guru.composecookbook.ui.advancelists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.dynamic.DynamicUIActivity
import com.guru.composecookbook.ui.lists.GridListView
import com.guru.composecookbook.ui.lists.VerticalListView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.advancelists.AdvanceListsActivity.Companion.tabs
import com.guru.composecookbook.ui.carousel.Pager
import com.guru.composecookbook.ui.carousel.PagerState

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
                        TopAppBar(title = { Text(text = "Advance Lists(In Progress)") })
                    }
                ) {
                   AdvanceListContent()
                }
            }
        }
    }

    companion object {
        val tabs = listOf("Shimmers", "Swipeable Lists")
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
            PagerState(clock, 0, 0, tabs.size -1)
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
            when(page) {
                0 -> ShimmerList()
                1 -> SwipeableLists()
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