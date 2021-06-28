package com.guru.composecookbook.ui.home.home_screen_options.flings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.ui.home.customfling.FlingListActivity
import com.guru.composecookbook.ui.home.customfling.FlingStateStore
import com.guru.composecookbook.ui.utils.TestTags
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.util.*

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
            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_EDITABLE)
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
            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_EDITABLE)
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
            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_EDITABLE)
                .assertIsDisplayed()
        }
    }

    @Test
    fun flingSettingsApplyButtonWithNativeSelection() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("native")
                .performClick()

            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_APPLY)
                .assertIsDisplayed()

            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_APPLY)
                .performClick()

            onNodeWithTag(TestTags.HOME_FLING_HEADER)
                .assertIsDisplayed()

            Assert.assertEquals(
                FlingStateStore.INSTANCE.type.name.lowercase(Locale.getDefault()),
                "native"
            )

        }
    }

    @Test
    fun flingSettingsApplyButtonWithSmoothSelection() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("smooth")
                .performClick()

            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_APPLY)
                .assertIsDisplayed()

            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_APPLY)
                .performClick()

            onNodeWithTag(TestTags.HOME_FLING_HEADER)
                .assertIsDisplayed()

            Assert.assertEquals(
                FlingStateStore.INSTANCE.type.name.lowercase(Locale.getDefault()),
                "smooth"
            )

        }
    }

    @Test
    fun flingSettingsApplyButtonWithCustomSelection() {
        launchInitialActivity()
        composeAndroidTestRule.apply {
            onNodeWithTag("custom")
                .performClick()

            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_APPLY)
                .assertIsDisplayed()

            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_APPLY)
                .performClick()

            onNodeWithTag(TestTags.HOME_FLING_HEADER)
                .assertIsDisplayed()

            Assert.assertEquals(
                FlingStateStore.INSTANCE.type.name.lowercase(Locale.getDefault()),
                "custom"
            )
        }
    }

    private fun launchInitialActivity() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_BUTTON, true)
                .performClick()
            onNodeWithTag(TestTags.HOME_FLING_SETTINGS_ROOT)
                .assertIsDisplayed()
        }
    }

}