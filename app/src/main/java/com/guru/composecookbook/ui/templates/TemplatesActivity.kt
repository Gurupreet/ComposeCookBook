package com.guru.composecookbook.ui.templates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.guru.composecookbook.charts.Charts
import com.guru.composecookbook.comingsoon.ComingSoon
import com.guru.composecookbook.login.LoginOnboarding
import com.guru.composecookbook.onboarding.OnBoardingScreen
import com.guru.composecookbook.paymentcard.AddPaymentScreen
import com.guru.composecookbook.profile.ProfileScreen
import com.guru.composecookbook.theme.ComposeCookBookTheme

class TemplatesActivity : ComponentActivity() {

    private val templateType: String by lazy { intent.getStringExtra(TYPE) ?: "Profiles" }
    private val darkTheme: Boolean by lazy { intent.getBooleanExtra(DARK_THEME, true) }

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
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
        else -> ComingSoon()
    }
}
