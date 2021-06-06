package com.guru.composecookbook

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class BottomNavigationBarTests {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    private val bottomNavigationBarTestTag: String = "bottom_navigation_bar"

    @Test
    fun bottomNavigationBarMustHaveFiveEntries() {
        composeAndroidTestRule.apply {
            onNodeWithTag(bottomNavigationBarTestTag)
                .onChild()
                .onChildren()
                .assertCountEquals(5)
        }
    }

    @Test
    fun bottomNavigationBarMustHaveHomeEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Home")
                .assertIsSelectable()
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveWidgetsEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Widgets")
                .assertIsSelectable()
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveAnimEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Anim")
                .assertIsSelectable()
                .assertExists()

        }
    }

    @Test
    fun bottomNavigationBarMustHaveDemoUiEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag("DemoUI")
                .assertIsSelectable()
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveTemplateEntry() {
        composeAndroidTestRule.apply {
            composeAndroidTestRule.apply {
                onNodeWithTag("Template")
                    .assertIsSelectable()
                    .assertExists()
            }
        }
    }

    @Test
    fun bottomNavigationBartHomeIsSelectedByDefault() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Home")
                .performClick()
                .assertIsSelected()
                .onSiblings()
                .assertAny(isNotSelected())
        }
    }

    @Test
    fun whenBottomNavEntryHomeClickedTheHomeScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Home")
                .performClick()

            onNodeWithTag("Home Screen")
                .assertIsDisplayed()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavEntryWidgetsClickedTheWidgetScreenIsDisplayed() {
        composeAndroidTestRule.apply {

            onNodeWithTag("Widgets")
                .performClick()

            onNodeWithTag("Widget Screen")
                .assertIsDisplayed()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavBarAnimEntryClickedAnimationScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Anim")
                .performClick()

            onNodeWithTag("Animation Screen")
                .assertIsDisplayed()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavBarDemoUiEntryClickedDemoUiScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithTag("DemoUI")
                .performClick()

            onNodeWithTag("Demo UI List Screen")
                .assertIsDisplayed()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavigationBarTemplateEntryClickedTemplateScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Template")
                .performClick()

            onNodeWithTag("Template Screen")
                .assertExists()
        }
    }


}
