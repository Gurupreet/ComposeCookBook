package com.guru.composecookbook.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the login screen. Types a valid email and password into the two fields, clicks "Log
 * In", and asserts the resulting state: the onLoginSuccess callback fires.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class LoginUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun login_validCredentialsInvokeOnLoginSuccess() {
    var loginSuccess = false
    composeTestRule.setContent { LoginScreen(onLoginSuccess = { loginSuccess = true }) }
    composeTestRule.onNodeWithText("Welcome Back").assertIsDisplayed()

    val emailField = composeTestRule.onAllNodes(isEditable()).get(0)
    emailField.performScrollTo()
    emailField.performTextInput("abc@gmail.com")
    val passwordField = composeTestRule.onAllNodes(isEditable()).get(1)
    passwordField.performScrollTo()
    passwordField.performTextInput("12334444")

    composeTestRule.onNodeWithText("Log In").performScrollTo()
    composeTestRule.onNodeWithText("Log In").performClick()
    assertTrue("valid credentials should invoke onLoginSuccess", loginSuccess)
  }
}
