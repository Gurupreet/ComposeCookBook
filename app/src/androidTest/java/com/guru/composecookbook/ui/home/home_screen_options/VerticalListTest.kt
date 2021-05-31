package com.guru.composecookbook.ui.home.home_screen_options

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.guru.composecookbook.MainActivity
import org.junit.Rule
import org.junit.Test

class VerticalListTest {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun homeListOfEntriesMustBeVisible() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Home Screen List of entries")
                .assertExists()
        }
    }

    @Test
    fun assertIfVerticalListButtonVisible() {
        composeAndroidTestRule.apply {
            onNodeWithTag("button-Vertical ListView")
                .assertIsDisplayed()
        }
    }

    @Test
    fun assertIfVerticalListIsVisible() {
        composeAndroidTestRule.apply {
            onNodeWithTag("button-Vertical ListView")
                .assertIsDisplayed()
                .performClick()

            onNodeWithTag("item-1")
                .assertIsDisplayed()
        }
    }

}