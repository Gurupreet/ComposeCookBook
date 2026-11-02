package com.guru.composecookbook.twitter

import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.twitter.components.icons.IconCounterPreview
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) tests for the twitter module. The preview is wrapped in the module
 * theme over a filled background. Only the light variant is captured: IconCounter hardcodes its
 * tint (Color.LightGray) rather than reading the theme palette, so a dark variant renders
 * byte-identical to the light one and would verify nothing about dark mode - the dark golden was
 * therefore dropped.
 *
 * Record / refresh goldens: `./gradlew :demos:twitter:recordRoborazziDebug` Verify against goldens:
 * `./gradlew :demos:twitter:verifyRoborazziDebug` Golden PNGs live in `src/test/screenshots/` and
 * are committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class TwitterScreenshotTest {

  @Test
  fun twitter_icon_counter_light() {
    captureRoboImage("src/test/screenshots/twitter_icon_counter.png") {
      ComposeCookBookTheme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          IconCounterPreview()
        }
      }
    }
  }
}
