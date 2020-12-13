package com.guru.composecookbook

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.guru.composecookbook.theme.AppThemeState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BottomNavigationBarTests {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var bottomNavigationBarAccessibilityText: String


    @Before
    fun setUp() {
        bottomNavigationBarAccessibilityText = InstrumentationRegistry.getInstrumentation()
            .targetContext
            .getString(R.string.a11y_bottom_navigation_bar)

        composeAndroidTestRule.setContent {
            MainAppContent(appThemeState = mutableStateOf(AppThemeState()))
        }
    }

    @Test
    fun bottomNavigationBarMustHaveFiveEntries() {
        composeAndroidTestRule.apply {
            onNodeWithLabel(bottomNavigationBarAccessibilityText)
                .onChildren()
                .assertCountEquals(5)
                .assertAll(hasClickAction())
        }
    }

    @Test
    fun bottomNavigationBarMustHaveHomeEntry() {
        composeAndroidTestRule.apply {
            onNodeWithLabel(bottomNavigationBarAccessibilityText)
                .onChildren()
                .filterToOne(matcher = hasText("Home").and(hasClickAction()))
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveWidgetsEntry() {
        composeAndroidTestRule.apply {
            onNodeWithLabel(bottomNavigationBarAccessibilityText)
                .onChildren()
                .filterToOne(matcher = hasText("Widgets").and(hasClickAction()))
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveAnimEntry() {
        composeAndroidTestRule.apply {
            onNodeWithLabel(bottomNavigationBarAccessibilityText)
                .onChildren()
                .filterToOne(matcher = hasText("Anim").and(hasClickAction()))
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveDemoUiEntry() {
        composeAndroidTestRule.apply {
            onNodeWithLabel(bottomNavigationBarAccessibilityText)
                .onChildren()
                .filterToOne(matcher = hasText("DemoUI").and(hasClickAction()))
                .assertExists()
        }
    }

    @Test
    fun bottomNavigationBarMustHaveProfileEntry() {
        composeAndroidTestRule.apply {
            onNodeWithLabel(bottomNavigationBarAccessibilityText)
                .onChildren()
                .filterToOne(matcher = hasText("Profile").and(hasClickAction()))
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

    @ExperimentalTesting
    @Test
    fun whenBottomNavEntryWidgetsClickedTheWidgetScreenIsDisplayed() {
        composeAndroidTestRule.apply {

            onNodeWithText("Widgets")
                .performClick()

            // The widgets screen contains infinite animations.
            // If we don's pause the clock, the test will wait forever and no assertion could be done
            clockTestRule.pauseClock()

            onRoot()
                .onChildren()
                .filterToOne(hasTestTag("Widget Screen"))
                .assertExists()
        }
    }

    @ExperimentalTesting
    @Test
    fun whenBottomNavBarAnimEntryClickedAnimationScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithText("Anim")
                .performClick()

            clockTestRule.pauseClock()

            onRoot()
                .onChildren()
                .filterToOne(hasTestTag("Animation Screen"))
                .assertExists()
        }
    }

    @ExperimentalTesting
    @Test
    fun whenBottomNavBarDemoUiEntryClickedDemoUiScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithText("DemoUI")
                .performClick()

            clockTestRule.pauseClock()

            onRoot()
                .onChildren()
                .filterToOne(hasTestTag("Demo UI List Screen"))
                .assertExists()
        }
    }

    @ExperimentalTesting
    @Test
    fun whenBottomNavigationBarProfileEntryClickedProfileScreenIsDisplayed() {
        composeAndroidTestRule.apply {
            onNodeWithText("Profile")
                .performClick()

            clockTestRule.pauseClock()

            onRoot()
                .onChildren()
                .filterToOne(hasTestTag("Profile Screen"))
                .assertExists()
        }
    }


}
