package com.guru.composecookbook.profile

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) tests for the profile template. The preview is wrapped in the
 * module theme over a filled background. ProfileScreen reads theme colors (primary, background,
 * onBackground) and fills its own background, so light and dark genuinely differ; both variants are
 * kept.
 *
 * Record / refresh goldens: `./gradlew :templates:profile:recordRoborazziDebug` Verify against
 * goldens: `./gradlew :templates:profile:verifyRoborazziDebug` Golden PNGs live in
 * `src/test/screenshots/` and are committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class ProfileScreenshotTest {

  @Test
  fun profile_screen_light() {
    captureRoboImage("src/test/screenshots/profile_screen.png") {
      ComposeCookBookMaterial3Theme(darkTheme = false) {
        MaterialTheme { Surface(color = MaterialTheme.colorScheme.background) { ProfileScreen() } }
      }
    }
  }

  @Test
  fun profile_screen_dark() {
    captureRoboImage("src/test/screenshots/profile_screen_dark.png") {
      ComposeCookBookMaterial3Theme(darkTheme = true) {
        MaterialTheme { Surface(color = MaterialTheme.colorScheme.background) { ProfileScreen() } }
      }
    }
  }
}
