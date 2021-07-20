package com.guru.composecookbook.ui.home

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.HomeScreenItems
import com.guru.composecookbook.theme.*
import com.guru.composecookbook.ui.home.advancelists.AdvanceListsActivity
import com.guru.composecookbook.ui.home.customfling.FlingListActivity
import com.guru.composecookbook.ui.home.dialogs.DialogsActivity
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity
import com.guru.composecookbook.ui.home.dynamic.DynamicUiType
import com.guru.composecookbook.ui.home.lists.ListViewActivity
import com.guru.composecookbook.ui.utils.TestTags
import java.util.*


@ExperimentalFoundationApi
@Composable
fun HomeScreen(appThemeState: MutableState<AppThemeState>) {
    val showMenu = remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.testTag(TestTags.HOME_SCREEN_ROOT),
        topBar = {
            TopAppBar(
                title = { Text(text = "Compose CookBook") },
                elevation = 8.dp,
                actions = {
                    IconButton(onClick = {
                        appThemeState.value = appThemeState
                            .value.copy(darkTheme = !appThemeState.value.darkTheme)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sleep),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { showMenu.value = !showMenu.value }) {
                        Icon(imageVector = Icons.Default.Palette, contentDescription = null)
                    }
                },
            )
        },
        content = {
            HomeScreenContent(appThemeState.value.darkTheme, showMenu) { newPalletSelected ->
                // Events can be and should be passed to as upper layer as possible here
                // we are just passing to till HomeScreen.
                appThemeState.value = appThemeState.value.copy(pallet = newPalletSelected)
                showMenu.value = false
            }
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun HomeScreenContent(
    isDarkTheme: Boolean,
    showMenu: MutableState<Boolean>,
    onPalletChange: (ColorPallet) -> Unit
) {
    val context = LocalContext.current
    val list = remember { DemoDataProvider.homeScreenListItems }
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isWiderScreen = screenWidth > 550 // Random number for now
    Box(modifier = Modifier.fillMaxSize()) {
        if (isWiderScreen) {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(
                    items = list,
                    itemContent = {
                        HomeScreenListView(it, context, isDarkTheme, isWiderScreen)
                    })
            }
        } else {
            LazyColumn(
                modifier = Modifier.testTag(TestTags.HOME_SCREEN_LIST)
            ) {
                items(
                    items = list,
                    itemContent = {
                        HomeScreenListView(it, context, isDarkTheme, isWiderScreen)
                    })
            }
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
        modifier = modifier
            .padding(8.dp)
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
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onPalletChange),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Filled.FiberManualRecord, tint = color, contentDescription = null)
        Text(text = name, modifier = Modifier.padding(8.dp))
    }
}


@Composable
fun HomeScreenListView(
    homeScreenItems: HomeScreenItems, context: Context, isDarkTheme: Boolean,
    isWiderScreen: Boolean
) {
    if (isWiderScreen) {
        Card(
            modifier = Modifier
                .clickable { homeItemClicked(homeScreenItems, context, isDarkTheme) }
                .height(150.dp)
                .padding(8.dp),
            backgroundColor = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            Text(
                text = homeScreenItems.name,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h6
            )
        }
    } else {
        Button(
            onClick = { homeItemClicked(homeScreenItems, context, isDarkTheme) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("button-${homeScreenItems.name}")
        ) {
            Text(
                text = homeScreenItems.name,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.button
            )
        }
    }
}

fun homeItemClicked(homeScreenItems: HomeScreenItems, context: Context, isDarkTheme: Boolean) {
    //TODO pass theme to following screens
    val intent = when (homeScreenItems) {
        is HomeScreenItems.ListView -> {
            ListViewActivity.newIntent(
                context,
                homeScreenItems.type.uppercase(Locale.getDefault()), isDarkTheme
            )
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
        HomeScreenItems.CustomFling -> {
            FlingListActivity.newIntent(context = context, isDarkTheme = isDarkTheme)
        }
        HomeScreenItems.MotionLayout -> {
            DynamicUIActivity.newIntent(context, DynamicUiType.MOTIONLAYOUT.name, isDarkTheme)
        }
    }
    context.startActivity(intent)
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewHomeScreen() {
    val state = remember {
        mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
    }
    HomeScreen(state)
}

