package com.guru.composecookbook.ui.dynamic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.carousel.CarouselLayout
import com.guru.composecookbook.ui.constraintlayout.ConstraintLayoutDemos
import com.guru.composecookbook.ui.widgets.Layouts


class DynamicUIActivity : AppCompatActivity() {

    private val dynamicUiType: String by lazy {
        intent?.getStringExtra(TYPE) ?: DynamicUiType.TABS.name
    }

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookTheme(isDarkTheme) {
                DynamicUiWrapper(dynamicUiType) {
                    onBackPressed()
                }
            }
        }
    }

    companion object {
        const val TYPE = "type"
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, dynamicUiType: String, isDarkTheme: Boolean) =
            Intent(context, DynamicUIActivity::class.java).apply {
                putExtra(TYPE, dynamicUiType)
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}


@Composable
fun DynamicUiWrapper(uiType: String, onback: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = uiType) },
                elevation = if (uiType == DynamicUiType.TABS.name) 0.dp else 8.dp,
                navigationIcon = {
                    IconButton(onClick = onback) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
        bodyContent = {
            // We setup a base activity and we will change content depending upon ui type so
            // we don't have to create Activity for every feature showcase
            when (uiType) {
                DynamicUiType.TABS.name -> {
                    TabLayout()
                }
                DynamicUiType.BOTTOMSHEET.name -> {
                    BottomSheetLayouts()
                }
                DynamicUiType.LAYOUTS.name -> {
                    Layouts()
                }
                DynamicUiType.CONSTRAINTLAYOUT.name -> {
                    ConstraintLayoutDemos()
                }
                DynamicUiType.CAROUSELL.name -> {
                    CarouselLayout()
                }
                DynamicUiType.MODIFIERS.name -> {
                    HowToModifiers()
                }
            }
        }
    )
}

@Preview
@Composable
fun previewDynamicUI() {
    DynamicUiWrapper(uiType = DynamicUiType.TABS.name, onback = {})
}


