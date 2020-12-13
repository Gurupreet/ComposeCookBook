package com.guru.composecookbook.ui.home

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.*
import com.guru.composecookbook.ui.advancelists.AdvanceListsActivity
import com.guru.composecookbook.ui.dialogs.DialogsActivity
import com.guru.composecookbook.ui.dynamic.DynamicUIActivity
import com.guru.composecookbook.ui.dynamic.DynamicUiType
import com.guru.composecookbook.ui.lists.ListViewActivity


@Composable
fun HomeScreen(appThemeState: MutableState<AppThemeState>) {
    val showMenu = remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.semantics { testTag = "Home Screen" },
        topBar = {
            TopAppBar(
                title = { Text(text = "Compose CookBook") },
                elevation = 8.dp,
                actions = {
                    IconButton(onClick = {
                        appThemeState.value = appThemeState
                            .value.copy(darkTheme = !appThemeState.value.darkTheme)
                    }) {
                        Icon(imageVector = vectorResource(id = R.drawable.ic_sleep))
                    }
                    IconButton(onClick = { showMenu.value = !showMenu.value }) {
                        Icon(imageVector = Icons.Default.Palette)
                    }
                },
            )
        },
        bodyContent = {
            HomeScreenContent(appThemeState.value.darkTheme, showMenu) { newPalletSelected ->
                // Events can be and should be passed to as upper layer as possible here
                // we are just passing to till HomeScreen.
                appThemeState.value = appThemeState.value.copy(pallet = newPalletSelected)
                showMenu.value = false
            }
        }
    )
}

@Composable
fun HomeScreenContent(
    isDarkTheme: Boolean,
    showMenu: MutableState<Boolean>,
    onPalletChange: (ColorPallet) -> Unit
) {
    val context = AmbientContext.current
    val list = remember { DemoDataProvider.homeScreenListItems }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumnFor(
            modifier = Modifier.semantics { testTag = "Home Screen List of entries" },
            items = list
        ) {
            HomeScreenListView(it, context, isDarkTheme)
        }
        PalletMenu(
            modifier = Modifier.align(Alignment.TopEnd),
            showMenu.value,
            onPalletChange
        )
    }
}

@Composable
fun PalletMenu(
    modifier: Modifier,
    showMenu: Boolean,
    onPalletChange: (ColorPallet) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp)
            .animateContentSize(),
        elevation = 8.dp
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            if (showMenu) {
                MenuItem(green500, "Green") {
                    onPalletChange.invoke(ColorPallet.GREEN)
                }
                MenuItem(purple, "Purple") {
                    onPalletChange.invoke(ColorPallet.PURPLE)
                }
                MenuItem(orange500, "Orange") {
                    onPalletChange.invoke(ColorPallet.ORANGE)
                }
                MenuItem(blue500, "Blue") {
                    onPalletChange.invoke(ColorPallet.BLUE)
                }
            } else {

            }
        }
    }
}

@Composable
fun MenuItem(color: Color, name: String, onPalletChange: () -> Unit) {
    Row(
        modifier = Modifier.padding(8.dp).clickable(onClick = onPalletChange),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Filled.FiberManualRecord, tint = color)
        Text(text = name, modifier = Modifier.padding(8.dp))
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
    //TODO pass theme to following screens
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
        HomeScreenItems.PullRefresh -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.PULLRERESH.name, isDarkTheme)
        }
    }
    context.startActivity(intent)
}

@Preview
@Composable
fun PreviewHomeScreen() {
    val state = mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
    HomeScreen(state)
}

