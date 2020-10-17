package com.guru.composecookbook.ui.home

import android.content.Context
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.ui.advancelists.AdvanceListsActivity
import com.guru.composecookbook.ui.dialogs.DialogsActivity
import com.guru.composecookbook.ui.dynamic.DynamicUIActivity
import com.guru.composecookbook.ui.dynamic.DynamicUiType
import com.guru.composecookbook.ui.lists.ListViewActivity


@Composable
fun HomeScreen(darkTheme: MutableState<Boolean>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Compose CookBook") },
                elevation = 8.dp,
                actions = {
                    IconButton(onClick = { darkTheme.value = !darkTheme.value }) {
                        Icon(asset = vectorResource(id = R.drawable.ic_sleep))
                    }
                }
            )
        },
        bodyContent = {
            HomeScreenContent(darkTheme.value)
        }
    )
}

@Composable
fun HomeScreenContent(isDarkTheme: Boolean) {
    val context = ContextAmbient.current
    val list = remember { DemoDataProvider.homeScreenListItems }
    LazyColumnFor(items = list) {
        HomeScreenListView(it, context, isDarkTheme)
    }
}

@Composable
fun HomeScreenListView(homeScreenItems: HomeScreenItems, context: Context, isDarkTheme: Boolean) {
    Button(
        onClick = { homeItemClicked(homeScreenItems, context, isDarkTheme) },
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text(
            text = homeScreenItems.name,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.button
        )
    }
}

fun homeItemClicked(homeScreenItems: HomeScreenItems, context: Context, isDarkTheme: Boolean) {
    val intent = when (homeScreenItems) {
        is HomeScreenItems.ListView -> {
            ListViewActivity.newIntent(context, homeScreenItems.type.toUpperCase(), isDarkTheme)
        }
        HomeScreenItems.Dialogs -> {
            DialogsActivity.newIntent(context, isDarkTheme)
        }
        HomeScreenItems.TabLayout -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.TABS.name, isDarkTheme)
        }
        HomeScreenItems.Carousel -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.CAROUSELL.name, isDarkTheme)
        }
        HomeScreenItems.ConstraintsLayout -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.CONSTRAINTLAYOUT.name, isDarkTheme)
        }
        HomeScreenItems.CollapsingAppBar -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.TABS.name, isDarkTheme)
        }
        HomeScreenItems.BottomAppBar -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.TABS.name, isDarkTheme)
        }
        HomeScreenItems.BottomSheets -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.BOTTOMSHEET.name, isDarkTheme)
        }
        HomeScreenItems.Layouts -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.LAYOUTS.name, isDarkTheme)
        }
        HomeScreenItems.Modifiers -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.MODIFIERS.name, isDarkTheme)
        }
        HomeScreenItems.AndroidViews -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.ANDROIDVIEWS.name, isDarkTheme)
        }
        HomeScreenItems.AdvanceLists -> {
            AdvanceListsActivity.newIntent(context, isDarkTheme)
        }
    }
    context.startActivity(intent)
}

@Preview
@Composable
fun PreviewHomeScreen() {
    val state = remember { mutableStateOf(false) }
    HomeScreen(state)
}

