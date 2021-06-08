package com.guru.composecookbook.ui.home.home_screen_options.flings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.ui.home.customfling.FlingListActivity
import com.guru.composecookbook.ui.utils.TestTags
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
            onNodeWithTag(TestTags.HOME_FLING_LIST_BUTTON)
                .assertIsDisplayed()
        }
    }

    @Test
    fun flingListOfHeaderMustBeRendered() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_FLING_HEADER)
                .assertIsDisplayed()
        }
    }

    @Test
    fun flingListOfLazyColumnMustBeRendered() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_FLING_LIST, true)
                .assertExists()
        }
    }

    @Test
    fun flingListClickOnSettingsButton() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_BUTTON, true)
                .performClick()
            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_ROOT)
                .assertIsDisplayed()
        }
    }
}
