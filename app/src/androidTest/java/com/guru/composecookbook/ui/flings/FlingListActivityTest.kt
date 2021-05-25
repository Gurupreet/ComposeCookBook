package com.guru.composecookbook.ui.flings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.ui.home.customfling.FlingListActivity
import org.junit.Rule
import org.junit.Test

/**
 * UI test for Fling List page.
 *
 * @author https://github.com/iamjosephmj
 */
class FlingListActivityTest {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<FlingListActivity>()

    @Test
    fun flingListOfButtonMustBeRendered() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Fling List Activity Button")
                .assertIsDisplayed()
        }
    }

    @Test
    fun flingListOfHeaderMustBeRendered() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Header")
                .assertIsDisplayed()
        }
    }

    @Test
    fun flingListOfLazyColumnMustBeRendered() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Fling List Activity List", true)
                .assertExists()
        }
    }

    @Test
    fun flingListClickOnSettingsButton() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Fling Settings", true)
                .performClick()
            onNodeWithTag("Settings Page")
                .assertIsDisplayed()
        }
    }
}
