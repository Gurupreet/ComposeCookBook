package com.guru.composecookbook.tiktok

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.tiktok.components.home.TiktokCreateIcon
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) tests for the tiktok module. The preview is wrapped in the module
 * theme over a filled background. Only the light variant is captured: TiktokCreateIcon hardcodes
 * Color.White / Color.Black, so a dark variant renders byte-identical to the light one and would
 * verify nothing about dark mode - the dark golden was therefore dropped.
 *
 * Record / refresh goldens: `./gradlew :demos:tiktok:recordRoborazziDebug` Verify against goldens:
 * `./gradlew :demos:tiktok:verifyRoborazziDebug` Golden PNGs live in `src/test/screenshots/` and
 * are committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class TiktokScreenshotTest {

  @Test
  fun tiktok_create_icon_light() {
    captureRoboImage("src/test/screenshots/tiktok_create_icon.png") {
      ComposeCookBookTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) { TiktokCreateIcon() }
      }
    }
  }
}
