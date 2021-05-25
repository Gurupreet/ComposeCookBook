package com.guru.composecookbook.ui.flings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.ui.home.customfling.FlingListActivity
import com.guru.composecookbook.ui.home.customfling.FlingStateStore
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

/**
 *
 * UI test for fling test page.
 *
 * @author https://github.com/iamjosephmj
 */
class FlingSettingsPageTest {
    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<FlingListActivity>()

    @Test
    fun flingSettingsIfHeaderIsDisplayed() {
        launchInitialActivity()
    }

    @Test
    fun flingSettingsNativeRadioButtonRendered() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("native")
                .assertIsDisplayed()
        }
    }

    @Test
    fun flingSettingsNativeRadioButtonClick() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("native")
                .performClick()
            onNodeWithTag("Custom Editable")
                .assertDoesNotExist()
        }
    }

    @Test
    fun flingSettingsSmoothRadioButtonRendered() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("smooth")
                .assertIsDisplayed()
        }
    }

    @Test
    fun flingSettingsSmoothRadioButtonClick() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("smooth")
                .performClick()
            onNodeWithTag("Custom Editable")
                .assertDoesNotExist()
        }
    }

    @Test
    fun flingSettingsCustomRadioButtonRendered() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("custom")
                .assertIsDisplayed()
        }
    }

    @Test
    fun flingSettingsCustomRadioButtonClick() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("custom")
                .performClick()
            onNodeWithTag("Custom Editable")
                .assertIsDisplayed()
        }
    }

    @Test
    fun flingSettingsApplyButtonWithNativeSelection() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("native")
                .performClick()

            onNodeWithTag("apply")
                .assertIsDisplayed()

            onNodeWithTag("apply")
                .performClick()

            onNodeWithTag("Header")
                .assertIsDisplayed()

            Assert.assertEquals(FlingStateStore.INSTANCE.type.name.toLowerCase(), "native")

        }
    }

    @Test
    fun flingSettingsApplyButtonWithSmoothSelection() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("smooth")
                .performClick()

            onNodeWithTag("apply")
                .assertIsDisplayed()

            onNodeWithTag("apply")
                .performClick()

            onNodeWithTag("Header")
                .assertIsDisplayed()

            Assert.assertEquals(FlingStateStore.INSTANCE.type.name.toLowerCase(), "smooth")

        }
    }

    @Test
    fun flingSettingsApplyButtonWithCustomSelection() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("custom")
                .performClick()

            onNodeWithTag("apply")
                .assertIsDisplayed()

            onNodeWithTag("apply")
                .performClick()

            onNodeWithTag("Header")
                .assertIsDisplayed()

            Assert.assertEquals(FlingStateStore.INSTANCE.type.name.toLowerCase(), "custom")
        }
    }

    private fun launchInitialActivity() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Fling Settings", true)
                .performClick()
            onNodeWithTag("Settings Page")
                .assertIsDisplayed()
        }
    }

}