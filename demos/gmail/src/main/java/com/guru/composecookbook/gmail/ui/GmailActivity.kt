package com.guru.composecookbook.gmail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.guru.composecookbook.gmail.ui.home.GmailScreen
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme

class GmailActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        setContent {
            ComposeCookBookMaterial3Theme {
                GmailScreen()
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, GmailActivity::class.java)
    }
}

@OptIn(ExperimentalFoundationApi::class,
ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeCookBookMaterial3Theme {
        GmailScreen()
    }
}