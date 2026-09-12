package com.guru.composecookbook.ui.home.contrast

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ContrastThemeActivityTest {

  @get:Rule val composeTestRule = createComposeRule()

  private val context = InstrumentationRegistry.getInstrumentation().targetContext

  @Test
  fun newIntentCarriesDarkThemeFlagWhenEnabled() {
    val intent = ContrastThemeActivity.newIntent(context, isDarkTheme = true)

    assertTrue(intent.getBooleanExtra(DynamicUIActivity.DARK_THEME, false))
  }

  @Test
  fun newIntentCarriesDarkThemeFlagWhenDisabled() {
    val intent = ContrastThemeActivity.newIntent(context, isDarkTheme = false)

    assertFalse(intent.getBooleanExtra(DynamicUIActivity.DARK_THEME, true))
  }

  @Test
  fun demoRendersUnderDarkTheme() {
    composeTestRule.setContent { ContrastThemeDemo(dark = true) }

    composeTestRule.onNodeWithText("Contrast-aware Material 3").assertIsDisplayed()
  }

  @Test
  fun demoRendersUnderLightTheme() {
    composeTestRule.setContent { ContrastThemeDemo(dark = false) }

    composeTestRule.onNodeWithText("Contrast-aware Material 3").assertIsDisplayed()
  }
}
