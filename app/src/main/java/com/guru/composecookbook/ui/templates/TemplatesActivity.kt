package com.guru.composecookbook.ui.templates

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.biometric.BiometricPrompt
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.guru.composecookbook.charts.Charts
import com.guru.composecookbook.comingsoon.ComingSoon
import com.guru.composecookbook.login.LoginOnboarding
import com.guru.composecookbook.onboarding.OnBoardingScreen
import com.guru.composecookbook.paymentcard.AddPaymentScreen
import com.guru.composecookbook.profile.ProfileScreen
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.pinlock.PinLockView

@ExperimentalFoundationApi
class TemplatesActivity : ComponentActivity() {

    private val templateType: String by lazy { intent.getStringExtra(TYPE) ?: "Profiles" }
    private val darkTheme: Boolean by lazy { intent.getBooleanExtra(DARK_THEME, true) }

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
     //   val prompt = createBiometricPrompt(this as FragmentActivity)
        setContent {
            ComposeCookBookTheme(darkTheme = darkTheme) {
                TemplateApp(templateType)
            }
        }
    }

    companion object {
        private const val TYPE = "type"
        private const val DARK_THEME = "darkTheme"

        fun newIntent(context: Context, templateType: String, isDarkTheme: Boolean) =
            Intent(context, TemplatesActivity::class.java).apply {
                putExtra(TYPE, templateType)
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun TemplateApp(templateType: String) {
    when (templateType) {
        "Profiles" -> ProfileScreen()
        "Login" -> LoginOnboarding()
        "On-boarding" -> OnBoardingScreen { }
        "Charts" -> Charts()
        "Adding Payment Card" -> AddPaymentScreen()
        "Pin Lock/BioMetric" -> PinLockView()
        else -> ComingSoon()
    }
}

private fun createBiometricPrompt(activity: FragmentActivity): BiometricPrompt {
    val executor = ContextCompat.getMainExecutor(activity)

    val callback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                //loginWithPassword() // Because in this app, the negative button allows the user
            // to enter an account password. This is completely optional and your app doesn’t have to do it.
            }
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            // Proceed with viewing the private encrypted message.
           // showEncryptedMessage(result.cryptoObject)
        }
    }
    val biometricPrompt = BiometricPrompt(activity, executor, callback)

    return biometricPrompt
}