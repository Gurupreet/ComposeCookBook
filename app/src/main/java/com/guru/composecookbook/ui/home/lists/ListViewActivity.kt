package com.guru.composecookbook.ui.home.lists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.instagram.components.InstagramStories
import com.guru.composecookbook.instagram.components.StoryListItem
import com.guru.composecookbook.theme.ComposeCookBookMaterialTheme
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.verticalgrid.VerticalGrid
import java.util.*

class ListViewActivity : ComponentActivity() {

    private val listType: String by lazy {
        intent?.getStringExtra(TYPE) ?: ListViewType.VERTICAL.name
    }

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseView(isDarkTheme) {
                ListViewContent(listType) {
                    onBackPressed()
                }
            }
        }
    }

    companion object {
        const val TYPE = "type"
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, listViewType: String, isDarkTheme: Boolean) =
            Intent(context, ListViewActivity::class.java).apply {
                putExtra(TYPE, listViewType)
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun BaseView(isDarkTheme: Boolean, content: @Composable () -> Unit) {
    ComposeCookBookMaterialTheme(isDarkTheme) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListViewContent(listType: String, onback: () -> Unit) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Text(text = "ListView")
                        Text(
                            text = listType.lowercase(Locale.getDefault()),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onback) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.cd_back),
                        )
                    }
                }, 
            )
        },
        content = {
            when (listType) {
                ListViewType.VERTICAL.name -> {
                    VerticalListView()
                }
                ListViewType.HORIZONTAL.name -> {
                    HorizontalListView()
                }
                ListViewType.GRID.name -> {
                    GridListView()
                }
                ListViewType.MIX.name -> {
                    HorizontalListView()
                }
            }
        }
    )
}

@Composable
fun VerticalListView() {
    val list = remember { DemoDataProvider.itemList }
    LazyColumn {
        items(
            items = list,
            itemContent = { item ->
                if ((item.id % 3) == 0) {
                    VerticalListItemSmall(item = item)
                } else {
                    VerticalListItem(item = item)
                }
                ListItemDivider()
            })
    }
}


@Composable
fun HorizontalListView() {
    val list = remember { DemoDataProvider.itemList }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Good Food",
            style = MaterialTheme.typography.labelMedium
        )
        LazyRow(
            modifier = Modifier.padding(end = 16.dp)
        ) {
            items(
                items = list,
                itemContent = {
                    HorizontalListItem(
                        it,
                        Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                })
        }
        ListItemDivider()
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Stories",
            style = MaterialTheme.typography.labelMedium
        )
        InstagramStories()
    }
}

@Composable
fun GridListView() {
    //TODO: NO IN-BUILT GRID VIEW NOT AVAILABLE YET USING ROWS FOR NOW
    // GRIDS are not lazy driven yet so let's wait for Lazy Layout to make grids
    val list = remember { DemoDataProvider.itemList.take(4) }
    val posts = remember { DemoDataProvider.tweetList }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        VerticalGrid(columns = 2) {
            list.forEach {
                GridListItem(item = it)
            }
        }
        VerticalGrid(columns = 4) {
            posts.forEach {
                StoryListItem(post = it)
            }
        }
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeCookBookTheme {
        ListViewContent(
            ListViewType.VERTICAL.name,
            onback = {})
    }
}