package com.guru.composecookbook.meditation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.meditation.ui.screen.MeditationHome
import com.guru.composecookbook.meditation.ui.theme.MeditationAppTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) tests for the meditation module. The preview is wrapped in the
 * module's own theme (MeditationAppTheme) over a filled background. Only the light variant is
 * captured: MeditationHome hardcodes its palette (DeepBlue / TextWhite from its own theme
 * constants), so a dark variant renders byte-identical to the light one and would verify nothing
 * about dark mode - the dark golden was therefore dropped.
 *
 * Record / refresh goldens: `./gradlew :demos:meditation:recordRoborazziDebug` Verify against
 * goldens: `./gradlew :demos:meditation:verifyRoborazziDebug` Golden PNGs live in
 * `src/test/screenshots/` and are committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
@OptIn(ExperimentalFoundationApi::class)
class MeditationScreenshotTest {

  @Test
  fun meditation_home_light() {
    captureRoboImage("src/test/screenshots/meditation_home.png") {
      MeditationAppTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) { MeditationHome() }
      }
    }
  }
}
