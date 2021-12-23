package com.guru.composecookbook.paint

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.guru.composecookbook.theme.ComposeCookBookTheme

class PaintActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class,
        androidx.compose.foundation.ExperimentalFoundationApi::class,
        androidx.compose.animation.ExperimentalAnimationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        setContent {
            ComposeCookBookTheme {
                PaintApp()
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, PaintActivity::class.java)
    }
}

@OptIn(ExperimentalComposeUiApi::class,
ExperimentalFoundationApi::class,
ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeCookBookTheme {
        PaintApp()
    }
}