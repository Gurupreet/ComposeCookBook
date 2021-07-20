package com.guru.composecookbook.ui.home.dynamic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.carousel.CarouselLayout
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.home.androidviews.AndroidViews
import com.guru.composecookbook.ui.home.constraintlayout.ConstraintLayoutDemos
import com.guru.composecookbook.ui.home.dialogs.BottomSheetLayouts
import com.guru.composecookbook.ui.home.motionlayout.MotionLayoutDemo
import com.guru.composecookbook.ui.home.pullrefreshdemos.PullRefreshList
import com.guru.composecookbook.ui.home.tabslayout.TabLayout
import com.guru.composecookbook.ui.learnwidgets.Layouts


class DynamicUIActivity : ComponentActivity() {

    private val dynamicUiType: String by lazy {
        intent?.getStringExtra(TYPE) ?: DynamicUiType.TABS.name
    }

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DARK_THEME, false) ?: false
    }

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
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


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun DynamicUiWrapper(uiType: String, onback: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = uiType) },
                elevation = if (uiType == DynamicUiType.TABS.name) 0.dp else 8.dp,
                navigationIcon = {
                    IconButton(onClick = onback) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
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
                DynamicUiType.ANDROIDVIEWS.name -> {
                    AndroidViews()
                }
                DynamicUiType.PULLRERESH.name -> {
                    PullRefreshList(onPullRefresh = {})
                }
                DynamicUiType.MOTIONLAYOUT.name -> {
                    MotionLayoutDemo()
                }
            }
        }
    )
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewDynamicUI() {
    DynamicUiWrapper(uiType = DynamicUiType.TABS.name, onback = {})
}


