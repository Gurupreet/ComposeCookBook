package com.guru.composecookbook.ui.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.guru.composecookbook.MainActivity
import com.guru.composecookbook.MainAppContent
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.AppThemeState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalMaterialApi
    @Before
    fun setUp() {
        composeAndroidTestRule.setContent {
            MainAppContent(appThemeState = mutableStateOf(AppThemeState()))
        }
    }


    @Test
    fun homeListOfEntriesMustBeVisible() {
        composeAndroidTestRule.apply {
            onNodeWithTag("Home Screen List of entries")
                .assertExists()
        }
    }


    @Test
    fun allDisplayedHomeScreenEntriesMustHaveAClickAction() {
        composeAndroidTestRule.apply {

            onNodeWithTag("Home Screen List of entries")
                .onChildren()
                .assertAll(
                    hasClickAction()
                )
        }
    }

    @Test
    fun allHomeScreenEntriesMustHaveTextFromTheDataProvider() {
        composeAndroidTestRule.apply {

            onNodeWithTag("Home Screen List of entries")
                .onChildren()
                .assertAll(
                    hasTextInProvidedDemoData(DemoDataProvider.homeScreenListItems)
                )
        }
    }

    /**
     * Returns whether the node's text matches exactly the text of the name in Home Screen Elements.
     *
     * @param homeScreenListItems the list with elements having the name to match.
     * @param ignoreCase Whether case should be ignored.
     *
     * @see hasText
     * @see SemanticsProperties.Text
     */
    private fun hasTextInProvidedDemoData(
        homeScreenListItems: List<HomeScreenItems>,
        ignoreCase: Boolean = false
    ) = SemanticsMatcher(
        description = "${SemanticsProperties.Text.name} is in '$homeScreenListItems' items' names (ignoreCase: $ignoreCase)"
    ) { semanticNode ->
        semanticNode.config.getOrNull(SemanticsProperties.Text)?.text.let { nodeText ->
            homeScreenListItems.any { it.name.equals(nodeText, ignoreCase) }
        }
    }
}



