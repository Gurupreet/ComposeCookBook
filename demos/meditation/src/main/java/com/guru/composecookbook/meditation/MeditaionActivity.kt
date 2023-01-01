package com.guru.composecookbook.meditation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.guru.composecookbook.meditation.ui.screen.MeditationHome
import com.guru.composecookbook.meditation.ui.theme.MeditationAppTheme

class MeditationActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationAppTheme {
                MeditationHome()
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MeditationActivity::class.java)
    }
}