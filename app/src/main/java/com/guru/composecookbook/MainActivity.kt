package com.guru.composecookbook

import FaIcons
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.android.gms.ads.MobileAds
import com.guru.composecookbook.theme.AppThemeState
import com.guru.composecookbook.theme.ColorPallet
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.theme.SystemUiController
import com.guru.composecookbook.theme.blue700
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.orange700
import com.guru.composecookbook.theme.purple700
import com.guru.composecookbook.ui.animation.AnimationScreen
import com.guru.composecookbook.ui.demoapps.DemoUIList
import com.guru.composecookbook.ui.home.HomeScreen
import com.guru.composecookbook.ui.home.PalletMenu
import com.guru.composecookbook.ui.learnwidgets.WidgetScreen
import com.guru.composecookbook.ui.templates.TemplateScreen
import com.guru.composecookbook.ui.utils.RotateIcon
import com.guru.composecookbook.ui.utils.TestTags
import com.guru.fontawesomecomposelib.FaIcon
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //for adView demo
        MobileAds.initialize(this)
        setContent {
            val systemUiController = remember { SystemUiController(window) }
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            BaseView(appTheme.value, systemUiController) {
                MainAppContent(appTheme)
            }
        }
    }
}

@Composable
fun BaseView(
    appThemeState: AppThemeState,
    systemUiController: SystemUiController?,
    content: @Composable () -> Unit
) {
    val color = when (appThemeState.pallet) {
        ColorPallet.GREEN -> green700
        ColorPallet.BLUE -> blue700
        ColorPallet.ORANGE -> orange700
        ColorPallet.PURPLE -> purple700
        else -> green700
    }
    ComposeCookBookMaterial3Theme(
        darkTheme = appThemeState.darkTheme,
        colorPallet = appThemeState.pallet
    ) {
        systemUiController?.setStatusBarColor(color = MaterialTheme.colorScheme.onPrimaryContainer, darkIcons = appThemeState.darkTheme)
        content()
    }
}

@OptIn(
    ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    appThemeState: MutableState<AppThemeState>,
    chooseColorBottomModalState: ModalBottomSheetState, //use for a11y
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colorScheme.background) {
                when (screen) {
                    BottomNavType.HOME -> HomeScreen(appThemeState, chooseColorBottomModalState)
                    BottomNavType.WIDGETS -> WidgetScreen()
                    BottomNavType.ANIMATION -> AnimationScreen()
                    BottomNavType.DEMOUI -> DemoUIList()
                    BottomNavType.TEMPLATE -> TemplateScreen(appThemeState.value.darkTheme)

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainAppContent(appThemeState: MutableState<AppThemeState>) {
    //Default home screen state is always HOME
    val homeScreenState = rememberSaveable { mutableStateOf(BottomNavType.HOME) }
    val bottomNavBarContentDescription = stringResource(id = R.string.a11y_bottom_navigation_bar)
    val chooseColorBottomModalState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = chooseColorBottomModalState,
        sheetContent = {
            //Modal used only when user use talkback for the sake of accessibility
            PalletMenu(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) { newPalletSelected ->
                appThemeState.value = appThemeState.value.copy(pallet = newPalletSelected)
                coroutineScope.launch {
                    chooseColorBottomModalState.hide()
                }
            }
        }
    ) {
        val config = LocalConfiguration.current
        val orientation = config.orientation
        if (orientation == ORIENTATION_PORTRAIT) {
            Column {
                HomeScreenContent(
                    homeScreen = homeScreenState.value,
                    appThemeState = appThemeState,
                    chooseColorBottomModalState = chooseColorBottomModalState,
                    modifier = Modifier.weight(1f)
                )
                BottomNavigationContent(
                    modifier = Modifier
                        .semantics { contentDescription = bottomNavBarContentDescription }
                        .testTag(TestTags.BOTTOM_NAV_TEST_TAG),
                    homeScreenState = homeScreenState
                )
            }
        } else {
            Row(modifier = Modifier.fillMaxSize()) {
                NavigationRailContent(
                    modifier = Modifier
                        .semantics { contentDescription = bottomNavBarContentDescription }
                        .testTag(TestTags.BOTTOM_NAV_TEST_TAG),
                    homeScreenState = homeScreenState
                )
                HomeScreenContent(
                    homeScreen = homeScreenState.value,
                    appThemeState = appThemeState,
                    chooseColorBottomModalState = chooseColorBottomModalState,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

}

@Composable
fun BottomNavigationContent(
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }
    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.Home,
                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
            },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_home),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
        )
        NavigationBarItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.Tools, tint = LocalContentColor
                        .current.copy(
                            alpha =
                            LocalContentAlpha.current
                        )
                )
            },
            selected = homeScreenState.value == BottomNavType.WIDGETS,
            onClick = {
                homeScreenState.value = BottomNavType.WIDGETS
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_widgets),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_WIDGETS_TEST_TAG)
        )
        NavigationBarItem(
            icon = {
                RotateIcon(
                    state = animate,
                    asset = Icons.Default.PlayArrow,
                    angle = 720f,
                    duration = 2000
                )
            },
            selected = homeScreenState.value == BottomNavType.ANIMATION,
            onClick = {
                homeScreenState.value = BottomNavType.ANIMATION
                animate = true
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_animation),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_ANIM_TEST_TAG)

        )
        NavigationBarItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.LaptopCode, tint = LocalContentColor.current.copy(
                        alpha =
                        LocalContentAlpha.current
                    )
                )
            },
            selected = homeScreenState.value == BottomNavType.DEMOUI,
            onClick = {
                homeScreenState.value = BottomNavType.DEMOUI
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_demoui),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_DEMO_UI_TEST_TAG)
        )
        NavigationBarItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.LayerGroup, tint = LocalContentColor
                        .current.copy(
                            alpha =
                            LocalContentAlpha.current
                        )
                )
            },
            selected = homeScreenState.value == BottomNavType.TEMPLATE,
            onClick = {
                homeScreenState.value = BottomNavType.TEMPLATE
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_profile),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_TEMPLATE_TEST_TAG)

        )
    }
}

@Composable
private fun NavigationRailContent(
    modifier: Modifier,
    homeScreenState: MutableState<BottomNavType>,
) {
    var animate by remember { mutableStateOf(false) }
    NavigationRail(
        modifier = modifier,
    ) {
        NavigationRailItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.Home,
                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
            },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_home),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
        )
        NavigationRailItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.Tools, tint = LocalContentColor
                        .current.copy(
                            alpha =
                            LocalContentAlpha.current
                        )
                )
            },
            selected = homeScreenState.value == BottomNavType.WIDGETS,
            onClick = {
                homeScreenState.value = BottomNavType.WIDGETS
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_widgets),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_WIDGETS_TEST_TAG)
        )
        NavigationRailItem(
            icon = {
                RotateIcon(
                    state = animate,
                    asset = Icons.Default.PlayArrow,
                    angle = 720f,
                    duration = 2000
                )
            },
            selected = homeScreenState.value == BottomNavType.ANIMATION,
            onClick = {
                homeScreenState.value = BottomNavType.ANIMATION
                animate = true
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_animation),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_ANIM_TEST_TAG)

        )
        NavigationRailItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.LaptopCode, tint = LocalContentColor.current.copy(
                        alpha =
                        LocalContentAlpha.current
                    )
                )
            },
            selected = homeScreenState.value == BottomNavType.DEMOUI,
            onClick = {
                homeScreenState.value = BottomNavType.DEMOUI
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_demoui),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_DEMO_UI_TEST_TAG)
        )
        NavigationRailItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.LayerGroup, tint = LocalContentColor
                        .current.copy(
                            alpha =
                            LocalContentAlpha.current
                        )
                )
            },
            selected = homeScreenState.value == BottomNavType.TEMPLATE,
            onClick = {
                homeScreenState.value = BottomNavType.TEMPLATE
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = R.string.navigation_item_profile),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_TEMPLATE_TEST_TAG)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val appThemeState = remember { mutableStateOf(AppThemeState(false, ColorPallet.GREEN)) }
    BaseView(appThemeState.value, null) {
        MainAppContent(appThemeState)
    }
}
