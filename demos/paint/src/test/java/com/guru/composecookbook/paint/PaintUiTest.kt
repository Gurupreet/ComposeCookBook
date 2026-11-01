package com.guru.composecookbook.paint

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.theme.ComposeCookBookTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the Paint demo. Initially the only clickable node is the brush FAB; tapping it opens
 * the brush-size list (strokes 1..50 step 5) as additional clickable nodes — the resulting-state
 * assertion is that the number of clickable nodes increases.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35])
class PaintUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun drawingTools_brushFabOpensSizeList() {
    val drawBrush = mutableStateOf(0f)
    val drawColor = mutableStateOf(Color.Black)
    composeTestRule.setContent {
      ComposeCookBookTheme(darkTheme = false) {
        DrawingTools(
          drawColor = drawColor,
          drawBrush = drawBrush,
          usedColors = mutableSetOf(),
        )
      }
    }

    val clickables = {
      composeTestRule.onAllNodes(hasClickAction()).fetchSemanticsNodes(false, null).size
    }
    val before = clickables()
    // The brush FAB is the initial clickable node.
    assertTrue("the brush FAB should be present initially", before >= 1)

    // Tap the FAB (first clickable): the brush-size list opens.
    composeTestRule.onAllNodes(hasClickAction()).get(0).performClick()
    val after = clickables()
    assertTrue(
      "opening the brush list should add stroke-size buttons",
      after > before,
    )
  }
}
