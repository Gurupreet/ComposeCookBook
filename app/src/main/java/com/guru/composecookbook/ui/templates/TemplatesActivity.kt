package com.guru.composecookbook.ui.templates

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.guru.composecookbook.charts.Charts
import com.guru.composecookbook.comingsoon.ComingSoon
import com.guru.composecookbook.login.LoginOnboarding
import com.guru.composecookbook.onboarding.OnBoardingScreen
import com.guru.composecookbook.paymentcard.AddPaymentScreen
import com.guru.composecookbook.profile.ProfileScreen
import com.guru.composecookbook.cascademenu.CascadeScreen
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.ui.home.clock.ClockDemo
import com.guru.composecookbook.ui.home.timer.TimerDemo
import com.guru.pinlock.PinLockView

/**
 * Activity responsible for displaying different templates based on the provided template type and dark theme setting.
 *
 * @property templateType The type of template to display, defaults to "Profiles" if not provided.
 * @property darkTheme Boolean indicating whether to use dark theme for the templates.
 */
@OptIn(ExperimentalFoundationApi::class)
class TemplatesActivity : ComponentActivity() {

    private val templateType: String by lazy { intent.getStringExtra(TYPE) ?: "Profiles" }
    private val darkTheme: Boolean by lazy { intent.getBooleanExtra(DARK_THEME, true) }

    /**
     * Creates the activity UI and sets up the necessary configurations and theme.
     */
    @OptIn(ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //   val prompt = createBiometricPrompt(this as FragmentActivity)
        setContent {
            ComposeCookBookMaterial3Theme(darkTheme = darkTheme) {
                androidx.compose.material3.Surface {
                    TemplateApp(templateType)
                }
            }
        }
    }

    companion object {
        private const val TYPE = "type"
        private const val DARK_THEME = "darkTheme"

        /**
         * Creates an intent to launch TemplatesActivity with the specified parameters.
         *
         * @param context The context from which the activity is launched.
         * @param templateType The type of template to display.
         * @param isDarkTheme Boolean indicating whether to use dark theme.
         * @return Intent to launch TemplatesActivity.
         */
        fun newIntent(context: Context, templateType: String, isDarkTheme: Boolean) =
            Intent(context, TemplatesActivity::class.java).apply {
                putExtra(TYPE, templateType)
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}


/**
 * Composable function that displays a specific template based on the provided template type.
 *
 * @param templateType The type of template to display.
 */
@OptIn(ExperimentalMaterial3Api::class,
ExperimentalFoundationApi::class,
ExperimentalAnimationApi::class,
ExperimentalMaterialApi::class)
@Composable
fun TemplateApp(templateType: String) {
    when (templateType) {
        "Profiles" -> ProfileScreen()
        "Login" -> LoginOnboarding()
        "On-boarding" -> OnBoardingScreen { }
        "Charts" -> Charts()
        "Adding Payment Card" -> AddPaymentScreen()
        "Pin Lock/BioMetric" -> PinLockView()
        "Timer" -> TimerDemo()
        "Clock View" -> ClockDemo()
        "Cascade Menu" -> CascadeScreen()
        else -> ComingSoon()
    }
}


/**
 * Creates and returns a BiometricPrompt instance for biometric authentication.
 *
 * @param activity The FragmentActivity context to create BiometricPrompt.
 * @return BiometricPrompt instance.
 */
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

    }
    val biometricPrompt = BiometricPrompt(activity, executor, callback)

    return biometricPrompt
}