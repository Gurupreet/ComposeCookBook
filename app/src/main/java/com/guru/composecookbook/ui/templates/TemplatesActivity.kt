package com.guru.composecookbook.ui.templates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.profile.ProfileScreen
import com.guru.composecookbook.ui.utils.ComingSoon

class TemplatesActivity : AppCompatActivity() {

    private val templateType: String by lazy { intent.getStringExtra(TYPE) ?: "Profiles" }
    private val darkTheme: Boolean by lazy { intent.getBooleanExtra(DARK_THEME, true) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookTheme {
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

@Composable
fun TemplateApp(templateType: String) {
    when (templateType) {
        "Profiles" -> ProfileScreen()
        else -> ComingSoon()
    }
}
