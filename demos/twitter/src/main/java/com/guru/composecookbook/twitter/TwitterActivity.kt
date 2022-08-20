package com.guru.composecookbook.twitter

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
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.twitter.components.TwitterHome

class TwitterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        setContent {
            ComposeCookBookMaterial3Theme {
                val tweets = remember { DemoDataProvider.tweetList }
                TwitterHome(
                    tweets = tweets,
                    onMessagesClick = { /*TODO*/ },
                    onRetweetClick = { /*TODO*/ },
                    onLikesClick = { /*TODO*/ },
                    onShareClick = { /*TODO*/ },
                    onNewTweetClicked = { /*TODO*/ }
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, TwitterActivity::class.java)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeCookBookMaterial3Theme {
        TwitterHome(
            tweets = DemoDataProvider.tweetList,
            onMessagesClick = { /*TODO*/ },
            onRetweetClick = { /*TODO*/ },
            onLikesClick = { /*TODO*/ },
            onShareClick = { /*TODO*/ },
            onNewTweetClicked = { /*TODO*/ }
        )
    }
}
