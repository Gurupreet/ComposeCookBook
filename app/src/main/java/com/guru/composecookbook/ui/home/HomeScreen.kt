package com.guru.composecookbook.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.R
import com.guru.composecookbook.data.model.HomeScreenItems
import com.guru.composecookbook.theme.AppThemeState
import com.guru.composecookbook.theme.ColorPallet
import com.guru.composecookbook.theme.blue500
import com.guru.composecookbook.theme.components.Material3Card
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.orange500
import com.guru.composecookbook.theme.purple
import com.guru.composecookbook.ui.home.advancelists.AdvanceListsActivity
import com.guru.composecookbook.ui.home.customfling.FlingListActivity
import com.guru.composecookbook.ui.home.dialogs.DialogsActivity
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity
import com.guru.composecookbook.ui.home.dynamic.DynamicUiType
import com.guru.composecookbook.ui.home.lists.ListViewActivity
import com.guru.composecookbook.ui.utils.TestTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * Sets up the home screen UI with a top bar and content area.
 * The top bar includes buttons for toggling the dark theme and opening a color selection menu.
 * The content area displays the home screen content, including a list of items and a color palette menu.
 *
 * @param appThemeState: A mutable state holding the current theme state (light or dark) and color palette.
 * @param chooseColorBottomModalState: State for controlling the bottom modal sheet used for choosing colors.
 */
@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun HomeScreen(
    appThemeState: MutableState<AppThemeState>,
    chooseColorBottomModalState: ModalBottomSheetState
) {
    val showMenu = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.testTag(TestTags.HOME_SCREEN_ROOT),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Compose CookBook") },
                actions = {
                    IconButton(onClick = {
                        appThemeState.value = appThemeState
                            .value.copy(darkTheme = !appThemeState.value.darkTheme)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sleep),
                            contentDescription = stringResource(id = com.guru.composecookbook.R.string.cd_dark_theme)
                        )
                    }
                    ChangeColorIconButton(coroutineScope, chooseColorBottomModalState, showMenu)
                },
            )
        },
        content = { paddingValues ->
            HomeScreenContent(
                isDarkTheme = appThemeState.value.darkTheme,
                showMenu = showMenu,
                modifier = Modifier.padding(paddingValues),
                onPalletChange = { newPalletSelected ->
                    // Events can be and should be passed to as upper layer as possible here
                    // we are just passing to till HomeScreen.
                    appThemeState.value = appThemeState.value.copy(pallet = newPalletSelected)
                    showMenu.value = false
                }
            )
        }
    )
}

/**
 * Adds an icon button that, when clicked, toggles the visibility of the color palette menu or
 * shows the modal bottom sheet for color selection if accessibility settings are enabled.
 *
 * @param coroutineScope: Scope for launching coroutines.
 * @param chooseColorBottomModalState: State for controlling the color selection modal bottom sheet.
 * @param showMenu: Mutable state to control the visibility of the color palette menu.
 */
@SuppressLint("ServiceCast")
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChangeColorIconButton(
    coroutineScope: CoroutineScope,
    chooseColorBottomModalState: ModalBottomSheetState,
    showMenu: MutableState<Boolean>
) {
    val accessibilityManager = LocalContext.current.getSystemService(Context.ACCESSIBILITY_SERVICE)
            as android.view.accessibility.AccessibilityManager
    IconButton(onClick = {
        if (accessibilityManager.isEnabled && accessibilityManager.isTouchExplorationEnabled) {
            //Showing modal bottom sheet instead when user use a screen reader (otherwise it's not accessible)
            coroutineScope.launch {
                chooseColorBottomModalState.show()
            }
        } else {
            showMenu.value = !showMenu.value
        }
    }) {
        Icon(
            imageVector = Icons.Default.Palette, contentDescription = stringResource(
                id = com.guru.composecookbook.R.string.cd_change_color
            )
        )
    }
}

/**
 * Displays a list of home screen items, adapting the layout based on screen width.
 * Shows a color palette menu if showMenu is true.
 *
 * @param isDarkTheme: Boolean indicating whether the dark theme is active.
 * @param showMenu: Mutable state to control the visibility of the color palette menu.
 * @param onPalletChange: Callback invoked when a new color palette is selected.
 * @param modifier: Modifier for styling the composable.
 */
@Composable
fun HomeScreenContent(
    isDarkTheme: Boolean,
    showMenu: MutableState<Boolean>,
    onPalletChange: (ColorPallet) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val list = remember { DemoDataProvider.homeScreenListItems }
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isWiderScreen = screenWidth > 550 // Random number for now
    Box(modifier = modifier.fillMaxSize()) {
        if (isWiderScreen) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(count = list.size) { index ->
                    HomeScreenListView(list[index], context, isDarkTheme, isWiderScreen)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.testTag(TestTags.HOME_SCREEN_LIST)
            ) {
                items(
                    items = list,
                    itemContent = {
                        HomeScreenListView(it, context, isDarkTheme, isWiderScreen)
                    }
                )
            }
        }
        if (showMenu.value) {
            PalletMenu(
                modifier = Modifier.align(Alignment.TopEnd),
                onPalletChange
            )
        }

    }
}

/**
 * Displays a list of color options for the user to choose from, invoking onPalletChange when a selection is made.
 *
 * @param modifier: Modifier for styling the composable.
 * @param onPalletChange: Callback invoked when a new color palette is selected.
 */
@Composable
fun PalletMenu(
    modifier: Modifier,
    onPalletChange: (ColorPallet) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .animateContentSize(),
        ) {
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MenuItem(dynamicLightColorScheme(LocalContext.current).primary, "Dynamic") {
                    onPalletChange.invoke(ColorPallet.WALLPAPER)
                }
            }
        }
    }
}

/**
 * Displays a row with an icon and text, triggering the onPalletChange callback when clicked.
 *
 * @param color: Color of the icon representing the palette.
 * @param name: Name of the color palette.
 * @param onPalletChange: Callback invoked when the menu item is clicked.
 */
@Composable
fun MenuItem(color: Color, name: String, onPalletChange: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onPalletChange),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.FiberManualRecord,
            tint = color,
            contentDescription = null
        )
        Text(text = name, modifier = Modifier.padding(8.dp))
    }
}

/**
 * Displays a card or button for each home screen item, adjusting the layout based on screen width.
 *
 * @param homeScreenItems: Item to display in the list.
 * @param context: Context for starting activities.
 * @param isDarkTheme: Boolean indicating whether the dark theme is active.
 * @param isWiderScreen: Boolean indicating if the screen width is wider than a certain threshold.
 */
@Composable
fun HomeScreenListView(
    homeScreenItems: HomeScreenItems, context: Context, isDarkTheme: Boolean,
    isWiderScreen: Boolean
) {
    if (isWiderScreen) {
        Material3Card(
            modifier = Modifier
                .clickable { homeItemClicked(homeScreenItems, context, isDarkTheme) }
                .height(150.dp)
                .padding(8.dp),
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Text(
                text = homeScreenItems.name,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleMedium
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
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

/**
 * Starts a new activity based on the clicked home screen item, passing the theme state to the new activity.
 *
 * @param homeScreenItems: Item that was clicked.
 * @param context: Context for starting activities.
 * @param isDarkTheme: Boolean indicating whether the dark theme is active.
 */
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
            DynamicUIActivity.newIntent(
                context,
                DynamicUiType.CONSTRAINTLAYOUT.name,
                isDarkTheme
            )
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

/**
 * Provides a preview of the HomeScreen composable with default theme state and modal bottom sheet state.
 */
@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PreviewHomeScreen() {
    val state = remember {
        mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
    }
    val chooseColorBottomModalState =
        rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    HomeScreen(state, chooseColorBottomModalState)
}

