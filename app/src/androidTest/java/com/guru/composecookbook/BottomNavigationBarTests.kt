package com.guru.composecookbook

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.guru.composecookbook.ui.utils.TestTags
import org.junit.Rule
import org.junit.Test

class BottomNavigationBarTests {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun bottomNavigationBarMustHaveFiveEntries() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_TEST_TAG)
                .onChild()
                .onChildren()
                .assertCountEquals(5)
        }
    }

    @Test
    fun bottomNavigationBarMustHaveHomeEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
                .assertIsSelectable()
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveWidgetsEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_WIDGETS_TEST_TAG)
                .assertIsSelectable()
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveAnimEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_ANIM_TEST_TAG)
                .assertIsSelectable()
                .assertExists()

        }
    }

    @Test
    fun bottomNavigationBarMustHaveDemoUiEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_DEMO_UI_TEST_TAG)
                .assertIsSelectable()
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveTemplateEntry() {
        composeAndroidTestRule.apply {
            composeAndroidTestRule.apply {
                onNodeWithTag(TestTags.BOTTOM_NAV_TEMPLATE_TEST_TAG)
                    .assertIsSelectable()
                    .assertExists()
            }
        }
    }

    @Test
    fun bottomNavigationBartHomeIsSelectedByDefault() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
                .performClick()
                .assertIsSelected()
                .onSiblings()
                .assertAny(isNotSelected())
        }
    }

    @Test
    fun whenBottomNavEntryHomeClickedTheHomeScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
                .performClick()

            onNodeWithTag(TestTags.HOME_SCREEN_ROOT)
                .assertIsDisplayed()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavEntryWidgetsClickedTheWidgetScreenIsDisplayed() {
        composeAndroidTestRule.apply {

            onNodeWithTag(TestTags.BOTTOM_NAV_WIDGETS_TEST_TAG)
                .performClick()

            onNodeWithTag(TestTags.WIDGET_SCREEN_ROOT)
                .assertIsDisplayed()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavBarAnimEntryClickedAnimationScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_ANIM_TEST_TAG)
                .performClick()

            onNodeWithTag(TestTags.ANIM_SCREEN_ROOT)
                .assertIsDisplayed()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavBarDemoUiEntryClickedDemoUiScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_DEMO_UI_TEST_TAG)
                .performClick()

            onNodeWithTag(TestTags.DEMO_SCREEN_ROOT)
                .assertIsDisplayed()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavigationBarTemplateEntryClickedTemplateScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithTag(TestTags.BOTTOM_NAV_TEMPLATE_TEST_TAG)
                .performClick()

            onNodeWithTag(TestTags.TEMPLATE_SCREEN_ROOT)
                .assertExists()
        }
    }


}
