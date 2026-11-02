package com.guru.composecookbook.paymentcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.guru.composecookbook.theme.ComposeCookBookTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * UI test for the payment-card template. Types into the four fields (card holder name, card number,
 * expiry, CVC) and asserts the resulting state: the expiry is rendered on the card preview as
 * "12/26" (unique to the card) and the holder name now appears in two places (the field and the
 * card preview).
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h640dp")
class PaymentCardUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  init {
    composeTestRule.enableAccessibilityChecks()
  }

  @Test
  fun addPayment_enteringDetailsReflectsOnCard() {
    composeTestRule.setContent { ComposeCookBookTheme(darkTheme = false) { AddPaymentScreen() } }
    composeTestRule.onNodeWithText("Card holder name").assertIsDisplayed()

    // name
    composeTestRule.onAllNodes(isEditable()).get(0).performTextInput("Gurupreet Singh")
    // card number
    composeTestRule.onAllNodes(isEditable()).get(1).performTextInput("1234567890123456")
    // expiry + cvc
    composeTestRule.onAllNodes(isEditable()).get(2).performTextInput("1226")
    composeTestRule.onAllNodes(isEditable()).get(3).performTextInput("123")

    // The entered expiry is rendered on the card preview as "12/26" (card-only text).
    composeTestRule.onNodeWithText("12/26").assertIsDisplayed()
    // The holder name now appears twice: once in the field and once on the card preview.
    val nameNodes =
      composeTestRule.onAllNodesWithText("Gurupreet Singh").fetchSemanticsNodes(false, null)
    assertTrue(
      "holder name should be reflected on the card as well as the field",
      nameNodes.size >= 2,
    )
  }
}
