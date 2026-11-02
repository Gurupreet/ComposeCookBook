package com.guru.composecookbook

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.theme.AppThemeState
import com.guru.composecookbook.theme.ColorPallet
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.ui.utils.TestTags
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the :app shell (bottom navigation + home screen), rendered under the JUnit Platform
 * via the junit-vintage-engine (see app/build.gradle.kts). The Home screen and Widgets screen use
 * only local resources (no network / Lottie / infinite animation), so they are deterministic under
 * Robolectric NATIVE.
 *
 * Two behaviors are asserted:
 * 1. the bottom navigation switches tabs (state + the resulting screen content), and
 * 2. the Home screen list shows its entries.
 *
 * The Home screen list rows are [com.guru.composecookbook.data.model.HomeScreenItems.ListView]
 * items whose button label is "<type> ListView" (e.g. "Vertical ListView"), so the assertions use
 * those full labels.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class AppBottomNavUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  private fun setShell(navState: androidx.compose.runtime.MutableState<BottomNavType>) {
    val appThemeState = mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
    composeTestRule.setContent {
      ComposeCookBookMaterial3Theme(darkTheme = false) {
        val chooseColorBottomModalState =
          rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        Column {
          HomeScreenContent(
            homeScreen = navState.value,
            appThemeState = appThemeState,
            chooseColorBottomModalState = chooseColorBottomModalState,
            modifier = Modifier.weight(1f),
          )
          BottomNavigationContent(modifier = Modifier, homeScreenState = navState)
        }
      }
    }
  }

  @Test
  fun bottomNav_switchesToWidgetsTab() {
    val navState = mutableStateOf(BottomNavType.HOME)
    setShell(navState)
    // Advance frames so the initial layout settles.
    composeTestRule.mainClock.advanceTimeByFrame()

    // Home tab is the initial selection.
    composeTestRule.onNodeWithText("Compose CookBook").assertIsDisplayed()

    composeTestRule.onNodeWithTag(TestTags.BOTTOM_NAV_WIDGETS_TEST_TAG).performClick()

    assertEquals(BottomNavType.WIDGETS, navState.value)
    // Crossfade animates the new screen in; advance the clock so the animation completes.
    composeTestRule.mainClock.advanceTimeByFrame()
    composeTestRule.onNodeWithText("Material 3 Components").assertIsDisplayed()
  }

  @Test
  fun homeScreenList_showsEntries() {
    val navState = mutableStateOf(BottomNavType.HOME)
    setShell(navState)
    // Advance frames so the initial layout (including the LazyColumn's first items) settles.
    composeTestRule.mainClock.advanceTimeByFrame()

    composeTestRule.onNodeWithText("Compose CookBook").assertIsDisplayed()
    // First list entries rendered by the Home screen list (labels are "<type> ListView").
    composeTestRule.onNodeWithText("Vertical ListView").assertIsDisplayed()
    composeTestRule.onNodeWithText("Horizontal ListView").assertIsDisplayed()
    composeTestRule.onNodeWithText("Grid ListView").assertIsDisplayed()
  }
}
