package com.guru.composecookbook.instagram

import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.instagram.components.profile.LikesSectionPreview
import com.guru.composecookbook.instagram.components.profile.ProfileInfoPreview
import com.guru.composecookbook.instagram.components.stories.StoryItemMePreview
import com.guru.composecookbook.instagram.components.stories.StoryItemPreview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) tests for the instagram module. Each @Preview composable is
 * captured on its own (one captureRoboImage per preview) and wrapped in the module theme over a
 * filled background, so light renders on a light background rather than the transparent (black)
 * window Robolectric uses. This also fixes the previous single-overlay capture where several
 * previews were drawn as siblings on top of each other.
 *
 * Record / refresh goldens: `./gradlew :demos:instagram:recordRoborazziDebug` Verify against
 * goldens: `./gradlew :demos:instagram:verifyRoborazziDebug` Golden PNGs live in
 * `src/test/screenshots/` and are committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class InstagramScreenshotTest {

  @Test
  fun story_item_light() {
    captureRoboImage("src/test/screenshots/instagram_story_item.png") {
      ComposeCookBookTheme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          StoryItemPreview()
        }
      }
    }
  }

  @Test
  fun story_item_dark() {
    captureRoboImage("src/test/screenshots/instagram_story_item_dark.png") {
      ComposeCookBookTheme(darkTheme = true) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          StoryItemPreview()
        }
      }
    }
  }

  @Test
  fun story_item_me_light() {
    captureRoboImage("src/test/screenshots/instagram_story_item_me.png") {
      ComposeCookBookTheme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          StoryItemMePreview()
        }
      }
    }
  }

  @Test
  fun story_item_me_dark() {
    captureRoboImage("src/test/screenshots/instagram_story_item_me_dark.png") {
      ComposeCookBookTheme(darkTheme = true) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          StoryItemMePreview()
        }
      }
    }
  }

  @Test
  fun profile_info_light() {
    captureRoboImage("src/test/screenshots/instagram_profile_info.png") {
      ComposeCookBookTheme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          ProfileInfoPreview()
        }
      }
    }
  }

  @Test
  fun profile_info_dark() {
    captureRoboImage("src/test/screenshots/instagram_profile_info_dark.png") {
      ComposeCookBookTheme(darkTheme = true) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          ProfileInfoPreview()
        }
      }
    }
  }

  @Test
  fun likes_section_light() {
    captureRoboImage("src/test/screenshots/instagram_likes_section.png") {
      ComposeCookBookTheme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          LikesSectionPreview()
        }
      }
    }
  }

  @Test
  fun likes_section_dark() {
    captureRoboImage("src/test/screenshots/instagram_likes_section_dark.png") {
      ComposeCookBookTheme(darkTheme = true) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          LikesSectionPreview()
        }
      }
    }
  }
}
