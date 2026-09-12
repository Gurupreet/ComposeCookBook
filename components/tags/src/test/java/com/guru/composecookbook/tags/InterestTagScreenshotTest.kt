package com.guru.composecookbook.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) test. Captures composables directly on the JVM via Robolectric — no
 * emulator, device, or host Activity required.
 *
 * Record / refresh goldens: `./gradlew :components:tags:recordRoborazziDebug` Verify against
 * goldens: `./gradlew :components:tags:verifyRoborazziDebug` Golden PNGs live in
 * `src/test/screenshots/` and are committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w360dp-h640dp")
class InterestTagScreenshotTest {

  @Test
  fun interestTag_default() {
    captureRoboImage("src/test/screenshots/interest_tag_default.png") {
      MaterialTheme {
        Surface { InterestTag(text = "Jetpack Compose", modifier = Modifier.padding(8.dp)) }
      }
    }
  }

  @Test
  fun interestTag_row() {
    captureRoboImage("src/test/screenshots/interest_tag_row.png") {
      MaterialTheme {
        Surface {
          Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            InterestTag(text = "Kotlin")
            InterestTag(text = "Android")
            InterestTag(text = "Compose")
          }
        }
      }
    }
  }
}
