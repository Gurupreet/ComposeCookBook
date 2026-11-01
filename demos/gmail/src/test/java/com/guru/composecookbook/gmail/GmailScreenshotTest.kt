package com.guru.composecookbook.gmail

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.gmail.ui.home.PreviewGmailItem
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) tests for the gmail module. The preview is wrapped in the module
 * theme over a filled background (Surface(color = colorScheme.background)) so light renders on a
 * light background rather than the transparent (black) window Robolectric uses. The list item reads
 * theme colors, so the light and dark goldens genuinely differ.
 *
 * Record / refresh goldens: `./gradlew :demos:gmail:recordRoborazziDebug` Verify against goldens:
 * `./gradlew :demos:gmail:verifyRoborazziDebug` Golden PNGs live in `src/test/screenshots/` and are
 * committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class GmailScreenshotTest {

  @Test
  fun gmail_list_item_light() {
    captureRoboImage("src/test/screenshots/gmail_list_item.png") {
      ComposeCookBookMaterial3Theme(darkTheme = false) {
        MaterialTheme {
          Surface(color = MaterialTheme.colorScheme.background) { PreviewGmailItem() }
        }
      }
    }
  }

  @Test
  fun gmail_list_item_dark() {
    captureRoboImage("src/test/screenshots/gmail_list_item_dark.png") {
      ComposeCookBookMaterial3Theme(darkTheme = true) {
        MaterialTheme {
          Surface(color = MaterialTheme.colorScheme.background) { PreviewGmailItem() }
        }
      }
    }
  }
}
