package com.guru.composecookbook.ui.home.home_screen_options

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import com.guru.composecookbook.MainActivity
import com.guru.composecookbook.ui.utils.TestTags
import org.junit.Rule
import org.junit.Test

/**
 * UI test for Layouts page.
 *
 * @author https://github.com/iamjosephmj
 */
class LayoutsTest {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun homeListOfEntriesMustBeVisible() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_SCREEN_LIST)
                .assertExists()
        }
    }

    @Test
    fun assertIfLayoutsButtonVisible() {
        composeAndroidTestRule.apply {
            onNodeWithTag("button-Layouts")
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfRowEndVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_ROW_END)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfRowCenterVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_ROW_CENTER)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfRowSpaceAroundVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_ROW_SPACE_AROUND)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfRowSpaceBetweenVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_ROW_SPACE_BETWEEN)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfRowSpaceEvenlyVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_ROW_SPACE_EVENLY)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfRowStartVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_ROW_START)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfColumnBottomVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_COLUMN_BOTTOM)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfColumnCenterVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_COLUMN_CENTER)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfColumnSpaceAroundVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_COLUMN_SPACE_AROUND)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfColumnSpaceBetweenVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_COLUMN_SPACE_BETWEEN)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfColumnSpaceEvenlyVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_COLUMN_SPACE_EVENLY)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfColumnTopVisible() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_COLUMN_TOP)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfBoxNoAlign() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_BOX_NO_ALIGN)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfBoxToCenterNoAlign() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_BOX_TOP_CENTER_AND_NO_ALIGN)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }


    @Test
    fun assertIfConstraintLayout() {
        clickOnLayoutButton()
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.HOME_LAYOUTS_CONSTRAINT_LAYOUT)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }


    private fun clickOnLayoutButton() {
        composeAndroidTestRule.apply {
            onNodeWithTag("button-Layouts")
                .assertIsDisplayed()
                .performClick()
        }
    }

}