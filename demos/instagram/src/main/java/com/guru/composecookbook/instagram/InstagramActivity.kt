package com.guru.composecookbook.instagram

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.guru.composecookbook.instagram.components.InstagramHome
import com.guru.composecookbook.theme.ComposeCookBookTheme

class InstagramActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        setContent {
            ComposeCookBookTheme {
                InstagramHome()
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, InstagramActivity::class.java)
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeCookBookTheme {
        InstagramHome()
    }
}
