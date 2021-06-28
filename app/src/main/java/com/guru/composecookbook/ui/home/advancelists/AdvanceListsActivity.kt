package com.guru.composecookbook.ui.home.advancelists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.carousel.Pager
import com.guru.composecookbook.carousel.PagerState
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.home.advancelists.AdvanceListsActivity.Companion.tabs
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity

class AdvanceListsActivity : ComponentActivity() {

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
    }

    @ExperimentalMaterialApi
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
                                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
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
        val tabs = listOf("Shimmers", "Animated Lists", "Swipeable Lists")
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, AdvanceListsActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@ExperimentalMaterialApi
@Composable
fun AdvanceListContent() {
    var selectedIndex by remember { mutableStateOf(0) }
    val pagerState: PagerState = run {
        remember {
            PagerState(0, 0, tabs.size - 1)
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview10() {
    ComposeCookBookTheme {
        //ShimmerList()
    }
}