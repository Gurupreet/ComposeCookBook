package com.guru.composecookbook.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

  @get:Rule val composeTestRule = createComposeRule()

  private fun setLoginContent(onLoginSuccess: () -> Unit = {}) {
    composeTestRule.setContent { LoginScreen(onLoginSuccess = onLoginSuccess) }
  }

  @Test
  fun loginFieldsAreDisplayed() {
    setLoginContent()

    composeTestRule.onNodeWithText("Email address").assertIsDisplayed()
    composeTestRule.onNodeWithText("Password").assertIsDisplayed()
  }

  @Test
  fun loginButtonIsDisplayed() {
    setLoginContent()

    composeTestRule.onNodeWithText("Log In").performScrollTo().assertIsDisplayed()
  }

  @Test
  fun typedEmailIsReflectedInTheField() {
    setLoginContent()

    composeTestRule.onNodeWithText("Email address").performTextInput("user@example.com")

    composeTestRule.onNodeWithText("user@example.com").assertIsDisplayed()
  }
}
