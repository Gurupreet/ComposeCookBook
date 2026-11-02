package com.guru.composecookbook.ui.home

import androidx.compose.runtime.mutableStateOf
import com.github.takahirom.roborazzi.captureRoboImage
import com.guru.composecookbook.theme.AppThemeState
import com.guru.composecookbook.theme.ColorPallet
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Roborazzi screenshot (golden) test for the :app Home screen. The Home screen list is driven
 * entirely by DemoDataProvider.homeScreenListItems (local, deterministic data) with no network,
 * Lottie, Random, or infinite animation, so it holds a stable baseline. It is wrapped in the app's
 * Material3 theme with a filled background so light renders light.
 *
 * The :app module runs unit tests on the JUnit Platform (useJUnitPlatform), so this Robolectric
 * JUnit 4 test is discovered via the junit-vintage-engine dependency declared in
 * app/build.gradle.kts.
 *
 * Other :app @Preview composables were not captured: they load network images (Coil), embed Lottie
 * animations, use MotionLayout, or depend on system dynamic color / live configuration, none of
 * which is deterministic under Robolectric NATIVE.
 *
 * Record / refresh goldens: `./gradlew :app:recordRoborazziDebug` Verify against goldens:
 * `./gradlew :app:verifyRoborazziDebug` Golden PNGs live in `src/test/screenshots/` and are
 * committed to the repo.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class HomeScreenScreenshotTest {

  @Test
  fun home_screen_light() {
    captureRoboImage("src/test/screenshots/home_screen.png") {
      ComposeCookBookMaterial3Theme(darkTheme = false) {
        val appThemeState = mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
        val showPaletteSheet = mutableStateOf(false)
        HomeScreen(appThemeState, showPaletteSheet)
      }
    }
  }
}
