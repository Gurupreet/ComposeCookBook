package com.guru.composecookbook.tiktok

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.tiktok.components.home.VideoOverLayUI
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the TikTok demo. The overlay is tested in isolation (without the video player) and
 * the infinite spin animation is stopped (mainClock.autoAdvance = false) before the click. Tapping
 * the profile picture asserts the resulting state: an OpenProfile event fires for the album. The
 * "tiktok_profile" testTag resolves to two nodes (the clickable Box and the inner image), so the
 * first matching node is targeted.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class TiktokUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun tiktok_profileTapFiresOpenProfile() {
    val album = AlbumsDataProvider.albums[0]
    var event: TiktokHomeInteractionEvents? = null
    composeTestRule.setContent {
      ComposeCookBookTheme(darkTheme = false) {
        VideoOverLayUI(
          album = album,
          tiktokInteractionEvents = { event = it },
        )
      }
    }
    // Stop the infinite animation before interacting.
    composeTestRule.mainClock.autoAdvance = false

    composeTestRule.onNodeWithText("@${album.artist}").assertIsDisplayed()
    composeTestRule.onNodeWithText(album.song).assertIsDisplayed()

    composeTestRule.onAllNodesWithTag("tiktok_profile").get(0).performClick()

    val openProfile = event as? TiktokHomeInteractionEvents.OpenProfile
    assertTrue("tapping the profile should fire OpenProfile", openProfile != null)
    assertTrue("the event should carry the album", openProfile?.album == album)
  }
}
