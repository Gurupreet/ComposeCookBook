package com.guru.pinlock

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
 * Roborazzi screenshot (golden) tests for the pin lock template. The preview is wrapped in the
 * module theme over a filled background. PinLockView reads theme colors (primaryContainer,
 * onPrimary, error) and self-fills its background, so light and dark genuinely differ; both
 * variants are kept.
 *
 * Record / refresh goldens: `./gradlew :templates:pinlock:recordRoborazziDebug` Verify against
 * goldens: `./gradlew :templates:pinlock:verifyRoborazziDebug` Golden PNGs live in
 * `src/test/screenshots/` and are committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class PinLockScreenshotTest {

  @Test
  fun pin_lock_light() {
    captureRoboImage("src/test/screenshots/pin_lock.png") {
      ComposeCookBookMaterial3Theme(darkTheme = false) {
        MaterialTheme { Surface(color = MaterialTheme.colorScheme.background) { PinLockView() } }
      }
    }
  }

  @Test
  fun pin_lock_dark() {
    captureRoboImage("src/test/screenshots/pin_lock_dark.png") {
      ComposeCookBookMaterial3Theme(darkTheme = true) {
        MaterialTheme { Surface(color = MaterialTheme.colorScheme.background) { PinLockView() } }
      }
    }
  }
}
