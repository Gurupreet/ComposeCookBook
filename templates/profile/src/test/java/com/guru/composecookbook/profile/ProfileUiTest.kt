package com.guru.composecookbook.profile

import android.app.Activity
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the profile template. Asserts the profile screen is displayed with the user name,
 * then clicks the "Compose Cookbook github" entry and asserts the resulting state: an ACTION_VIEW
 * intent targeting the repository URL is started.
 *
 * Uses createAndroidComposeRule (rather than the headless createComposeRule) so the rule's
 * ComponentActivity is available to inspect the started intent.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class ProfileUiTest {
  @get:Rule
  val composeTestRule:
    AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity> =
    createAndroidComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun profile_githubLinkStartsRepositoryIntent() {
    composeTestRule.setContent {
      ComposeCookBookMaterial3Theme(darkTheme = false) { ProfileScreen() }
    }
    composeTestRule.onNodeWithTag("Profile Screen").assertIsDisplayed()
    composeTestRule.onNodeWithText("Gurupreet Singh").assertIsDisplayed()

    val github = composeTestRule.onNodeWithText("Compose Cookbook github")
    github.performScrollTo()
    github.performClick()

    val shadow: org.robolectric.shadows.ShadowActivity =
      Shadows.shadowOf(composeTestRule.activity as Activity)
    val started = shadow.getNextStartedActivity()
    assertEquals(Uri.parse("https://github.com/Gurupreet/ComposeCookBook"), started.data)
  }
}
