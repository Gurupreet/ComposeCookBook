package com.guru.composecookbook.carousel

import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.theme.ComposeCookBookTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) tests for the carousel module. Each @Preview composable is captured
 * on its own (one captureRoboImage per preview) and wrapped in the module theme over a filled
 * background (Surface(color = colorScheme.background)), so light renders light on a real background
 * rather than on the transparent (black) window Robolectric uses.
 *
 * Record / refresh goldens: `./gradlew :components:carousel:recordRoborazziDebug` Verify against
 * goldens: `./gradlew :components:carousel:verifyRoborazziDebug` Golden PNGs live in
 * `src/test/screenshots/` and are committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class CarouselScreenshotTest {

  @Test
  fun carousel_dot_light() {
    captureRoboImage("src/test/screenshots/carousel_dot.png") {
      ComposeCookBookTheme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          PreviewPaginationPionters()
        }
      }
    }
  }

  @Test
  fun carousel_dot_dark() {
    captureRoboImage("src/test/screenshots/carousel_dot_dark.png") {
      ComposeCookBookTheme(darkTheme = true) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          PreviewPaginationPionters()
        }
      }
    }
  }

  @Test
  fun carousel_layout_light() {
    captureRoboImage("src/test/screenshots/carousel_layout.png") {
      ComposeCookBookMaterial3Theme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          PreviewCarouselLayout()
        }
      }
    }
  }

  @Test
  fun carousel_layout_dark() {
    captureRoboImage("src/test/screenshots/carousel_layout_dark.png") {
      ComposeCookBookMaterial3Theme(darkTheme = true) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          PreviewCarouselLayout()
        }
      }
    }
  }

  @Test
  fun carousel_item_light() {
    captureRoboImage("src/test/screenshots/carousel_item.png") {
      ComposeCookBookMaterial3Theme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          PreviewCarouselItem()
        }
      }
    }
  }

  @Test
  fun carousel_item_dark() {
    captureRoboImage("src/test/screenshots/carousel_item_dark.png") {
      ComposeCookBookMaterial3Theme(darkTheme = true) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          PreviewCarouselItem()
        }
      }
    }
  }

  @Test
  fun carousel_item_circle_light() {
    captureRoboImage("src/test/screenshots/carousel_item_circle.png") {
      ComposeCookBookMaterial3Theme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          PreviewCarouselItemCircle()
        }
      }
    }
  }

  @Test
  fun carousel_item_circle_dark() {
    captureRoboImage("src/test/screenshots/carousel_item_circle_dark.png") {
      ComposeCookBookMaterial3Theme(darkTheme = true) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          PreviewCarouselItemCircle()
        }
      }
    }
  }
}
