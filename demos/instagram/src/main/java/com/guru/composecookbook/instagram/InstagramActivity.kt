package com.guru.composecookbook.instagram

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.instagram.components.InstagramHome
import com.guru.composecookbook.theme.ComposeCookBookTheme

class InstagramActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        setContent {
            val posts = remember { DemoDataProvider.tweetList.filter { it.tweetImageId != 0 } }
            val profiles = remember { DemoDataProvider.tweetList }
            ComposeCookBookTheme {
                InstagramHome(
                    posts = posts,
                    profiles = profiles,
                    onLikeClicked = {},
                    onCommentsClicked = {},
                    onSendClicked = {},
                    onProfileClicked = {},
                    onMessagingClicked = {}
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, InstagramActivity::class.java)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeCookBookTheme {
        InstagramHome(
            posts = DemoDataProvider.tweetList.filter { it.tweetImageId != 0 },
            profiles = DemoDataProvider.tweetList,
            onLikeClicked = {},
            onCommentsClicked = {},
            onSendClicked = {},
            onProfileClicked = {},
            onMessagingClicked = {}
        )
    }
}
