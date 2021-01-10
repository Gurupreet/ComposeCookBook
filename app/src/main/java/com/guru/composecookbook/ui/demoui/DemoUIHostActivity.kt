package com.guru.composecookbook.ui.demoui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.gmail.home.GmailScreen
import com.guru.composecookbook.ui.demoui.instagram.InstagramHome
import com.guru.composecookbook.ui.demoui.twitter.TwitterHome
import com.guru.composecookbook.ui.demoui.youtube.YoutubeHome
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity
import com.guru.composecookbook.ui.home.dynamic.DynamicUiType

class DemoUIHostActivity : AppCompatActivity() {

    private val demoUiType: String by lazy {
        intent?.getStringExtra(DynamicUIActivity.TYPE) ?: DynamicUiType.TABS.name
    }

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        setContent {
            ComposeCookBookTheme {
                DemoUIContent(demoUiType)
            }
        }
    }

    companion object {
        const val TYPE = "type"
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, demoUiType: String, isDarkTheme: Boolean) =
            Intent(context, DemoUIHostActivity::class.java).apply {
                putExtra(TYPE, demoUiType)
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun DemoUIContent(demoUiType: String) {
    when (demoUiType) {
        "Instagram" -> InstagramHome()
        "Twitter" -> TwitterHome()
        "Gmail" -> GmailScreen()
        "Youtube" -> YoutubeHome()
        else -> Text(text = "Comming soon", style = typography.h6)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeCookBookTheme {
        DemoUIContent(demoUiType = "Instagram")
    }
}