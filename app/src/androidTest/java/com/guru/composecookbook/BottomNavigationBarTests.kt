package com.guru.composecookbook

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.guru.composecookbook.theme.AppThemeState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BottomNavigationBarTests {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    private val bottomNavigationBarTestTag: String = "bottom_navigation_bar"


    @ExperimentalMaterialApi
    @Before
    fun setUp() {

        composeAndroidTestRule.setContent {
            MainAppContent(appThemeState = mutableStateOf(AppThemeState()))
        }
    }

    @Test
    fun bottomNavigationBarMustHaveFiveEntries() {
        composeAndroidTestRule.apply {
            onNodeWithTag(bottomNavigationBarTestTag)
                .onChildren()
                .assertCountEquals(5)
                .assertAll(hasClickAction())
        }
    }

    @Test
    fun bottomNavigationBarMustHaveHomeEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag(bottomNavigationBarTestTag)
                .onChildren()
                .filterToOne(matcher = hasText("Home").and(hasClickAction()))
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveWidgetsEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag(bottomNavigationBarTestTag)
                .onChildren()
                .filterToOne(matcher = hasText("Widgets").and(hasClickAction()))
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveAnimEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag(bottomNavigationBarTestTag)
                .onChildren()
                .filterToOne(matcher = hasText("Anim").and(hasClickAction()))
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveDemoUiEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag(bottomNavigationBarTestTag)
                .onChildren()
                .filterToOne(matcher = hasText("DemoUI").and(hasClickAction()))
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveTemplateEntry() {
        composeAndroidTestRule.apply {
            onNodeWithTag(bottomNavigationBarTestTag)
                .onChildren()
                .filterToOne(matcher = hasText("Template").and(hasClickAction()))
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBartHomeIsSelectedByDefault() {
        composeAndroidTestRule.apply {
            onNodeWithText("Home")
                .assertHasClickAction()
                .assertIsSelected()
                .onSiblings()
                .assertAny(isNotSelected())
        }
    }

    @Test
    fun whenBottomNavEntryHomeClickedTheHomeScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithText("Home")
                .performClick()     // Click on the Home entry in the bottom navigation bar
                .onAncestors()      // returns BottomNavigation, BottomNavigationContent, Column, MainAppContent, BaseView
                .onLast()           // returns BaseView - which is the root
                .onChildren()
                .filterToOne(hasTestTag("Home Screen"))
                .assertExists()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavEntryWidgetsClickedTheWidgetScreenIsDisplayed() {
        composeAndroidTestRule.apply {

            onNodeWithText("Widgets")
                .performClick()

            // The widgets screen contains infinite animations.
            // If we don's pause the clock, the test will wait forever and no assertion could be done

            onRoot()
                .onChildren()
                .filterToOne(hasTestTag("Widget Screen"))
                .assertExists()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavBarAnimEntryClickedAnimationScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithText("Anim")
                .performClick()


            onRoot()
                .onChildren()
                .filterToOne(hasTestTag("Animation Screen"))
                .assertExists()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavBarDemoUiEntryClickedDemoUiScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithText("DemoUI")
                .performClick()


            onRoot()
                .onChildren()
                .filterToOne(hasTestTag("Demo UI List Screen"))
                .assertExists()
        }
    }

    @ExperimentalTestApi
    @Test
    fun whenBottomNavigationBarTemplateEntryClickedTemplateScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithText("Template")
                .performClick()


            onRoot()
                .onChildren()
                .filterToOne(hasTestTag("Template Screen"))
                .assertExists()
        }
    }


}
