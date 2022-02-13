package com.guru.composecookbook.ui.home.advancelists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.carousel.Pager
import com.guru.composecookbook.carousel.PagerState
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.home.advancelists.AdvanceListsActivity.Companion.tabs
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity

class AdvanceListsActivity : ComponentActivity() {

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
    }

    @OptIn(ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookMaterial3Theme(isDarkTheme) {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        SmallTopAppBar(
                            title = { Text(text = "Advance Lists(In Progress)") },
                            navigationIcon = {
                                IconButton(onClick = { onBackPressed() }) {
                                    Icon(
                                        Icons.Filled.ArrowBack,
                                        contentDescription = stringResource(id = R.string.cd_back)
                                    )
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdvanceListContent() {
    var selectedIndex by remember { mutableStateOf(0) }
    val pagerState: PagerState = run {
        remember {
            PagerState(0, 0, tabs.size - 1)
        }
    }
    Column {
        ScrollableTabRow(
            backgroundColor = MaterialTheme.colorScheme.surface,
            selectedTabIndex = selectedIndex,
            edgePadding = 12.dp
        ) {
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
            when (commingPage) {
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