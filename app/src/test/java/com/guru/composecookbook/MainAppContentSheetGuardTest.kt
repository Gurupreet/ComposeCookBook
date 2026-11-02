package com.guru.composecookbook

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.theme.AppThemeState
import com.guru.composecookbook.theme.ColorPallet
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.ui.utils.TestTags
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Guard test for the :app palette bottom sheet (F1 / F9).
 *
 * The M3 [ModalBottomSheet] shows itself when it enters composition. If it is composed
 * unconditionally (the regression this guards), the palette sheet ("Green" / "Purple" / "Orange" /
 * "Blue" / "Dynamic") is visible at launch on top of the Home screen, and after dismissing it the
 * sheet's invisible "Close sheet" scrim keeps swallowing taps so the bottom navigation no longer
 * switches tabs.
 *
 * This test composes [MainAppContent] under the app's Material3 theme and asserts:
 * 1. no palette sheet content and no "Close sheet" node is displayed at rest, and
 * 2. tapping the Widgets nav item switches to the Material 3 Components screen.
 *
 * It runs on the JUnit Platform via the junit-vintage-engine (see app/build.gradle.kts).
 */
@OptIn(ExperimentalMaterial3Api::class)
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class MainAppContentSheetGuardTest {
  @get:Rule val composeTestRule = createComposeRule()

  /** True when a node with the exact text [t] is present and displayed; false otherwise. */
  private fun displayed(t: String): Boolean =
    try {
      composeTestRule.onNodeWithText(t).assertIsDisplayed()
      true
    } catch (e: Throwable) {
      false
    }

  @Test
  fun paletteSheet_notShownAtRest_andNavStillSwitches() {
    val appThemeState = mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
    composeTestRule.setContent {
      ComposeCookBookMaterial3Theme(darkTheme = false) { MainAppContent(appThemeState) }
    }
    composeTestRule.mainClock.advanceTimeByFrame()
    composeTestRule.mainClock.advanceTimeByFrame()

    // The Home screen is shown at rest.
    composeTestRule.onNodeWithText("Compose CookBook").assertIsDisplayed()

    // The palette sheet must not be visible at rest: none of its items, and no "Close sheet"
    // scrim node, may be displayed. On the unfixed (unconditionally composed) sheet these palette
    // items are shown on top of the Home screen, which is what this guard catches.
    for (label in listOf("Green", "Purple", "Orange", "Blue", "Dynamic", "Close sheet")) {
      assertFalse(
        "palette sheet '$label' is displayed at rest (the bottom sheet should be hidden)",
        displayed(label),
      )
    }

    // The bottom navigation still switches tabs (the regression made this impossible once the
    // sheet's invisible scrim swallowed every tap).
    composeTestRule.onNodeWithTag(TestTags.BOTTOM_NAV_WIDGETS_TEST_TAG).performClick()
    composeTestRule.mainClock.advanceTimeByFrame()
    composeTestRule.onNodeWithText("Material 3 Components").assertIsDisplayed()
  }
}
