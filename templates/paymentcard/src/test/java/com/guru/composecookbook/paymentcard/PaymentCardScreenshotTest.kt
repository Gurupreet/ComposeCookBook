package com.guru.composecookbook.paymentcard

import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.theme.ComposeCookBookTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) tests for the payment card template. The preview is wrapped in the
 * module theme over a filled background. Only the light variant is captured: PaymentCard hardcodes
 * its colors (theme-independent card background + white text), so a dark variant renders
 * byte-identical to the light one and would verify nothing about dark mode - the dark golden was
 * therefore dropped.
 *
 * Record / refresh goldens: `./gradlew :templates:paymentcard:recordRoborazziDebug` Verify against
 * goldens: `./gradlew :templates:paymentcard:verifyRoborazziDebug` Golden PNGs live in
 * `src/test/screenshots/` and are committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class PaymentCardScreenshotTest {

  @Test
  fun payment_card_light() {
    captureRoboImage("src/test/screenshots/payment_card.png") {
      ComposeCookBookTheme(darkTheme = false) {
        androidx.compose.material3.Surface(
          color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
          PreviewPaymentCard()
        }
      }
    }
  }
}
