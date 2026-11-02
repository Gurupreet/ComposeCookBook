package com.guru.composecookbook.paint

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.theme.ComposeCookBookTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) tests for the paint module. The preview is wrapped in the module
 * theme over a filled background. PaintApp is a full-screen M2 Scaffold (top app bar + canvas), so
 * light and dark differ via the theme's bar/text colors; both variants are kept.
 *
 * Record / refresh goldens: `./gradlew :demos:paint:recordRoborazziDebug` Verify against goldens:
 * `./gradlew :demos:paint:verifyRoborazziDebug` Golden PNGs live in `src/test/screenshots/` and are
 * committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class PaintScreenshotTest {

  @Test
  fun paint_light() {
    captureRoboImage("src/test/screenshots/paint.png") {
      ComposeCookBookTheme(darkTheme = false) {
        MaterialTheme { Surface(color = MaterialTheme.colors.background) { PaintApp() } }
      }
    }
  }

  @Test
  fun paint_dark() {
    captureRoboImage("src/test/screenshots/paint_dark.png") {
      ComposeCookBookTheme(darkTheme = true) {
        MaterialTheme { Surface(color = MaterialTheme.colors.background) { PaintApp() } }
      }
    }
  }
}
