package com.guru.composecookbook.ui.demoapps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.cryptoapp.ui.home.CryptoHomeActivity
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.datingapp.DatingHomeActivity
import com.guru.composecookbook.gmail.ui.GmailActivity
import com.guru.composecookbook.instagram.InstagramActivity
import com.guru.composecookbook.meditation.MeditationActivity
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeActivity
import com.guru.composecookbook.paint.PaintActivity
import com.guru.composecookbook.spotify.ui.home.SpotifyActivity
import com.guru.composecookbook.tiktok.TiktokActivity
import com.guru.composecookbook.twitter.TwitterActivity
import com.guru.composecookbook.ui.utils.TestTags
import com.guru.composecookbook.youtube.YoutubeActivity

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoUIList() {
    val demoUis = remember { DemoDataProvider.demoUiList }
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.testTag(TestTags.DEMO_SCREEN_ROOT)
    ) {
        LazyColumn {
            items(
                count = demoUis.size,
                itemContent = { index ->
                    val title = demoUis[index]
                    Button(
                        onClick = {
                            when (title) {
                                "Instagram" -> {
                                    context.startActivity(
                                        InstagramActivity.newIntent(context)
                                    )
                                }
                                "Twitter" -> {
                                    context.startActivity(
                                        TwitterActivity.newIntent(context)
                                    )
                                }
                                "Youtube" -> {
                                    context.startActivity(
                                        YoutubeActivity.newIntent(context)
                                    )
                                }
                                "Gmail" -> {
                                    context.startActivity(
                                        GmailActivity.newIntent(context)
                                    )
                                }
                                "Paint" -> {
                                    context.startActivity(
                                        PaintActivity.newIntent(context)
                                    )
                                }
                                "Spotify" -> {
                                    context.startActivity(
                                        SpotifyActivity.newIntent(context, false)
                                    )
                                }
                                "CryptoApp+MVVM" -> {
                                    context.startActivity(
                                        CryptoHomeActivity.newIntent(context, false)
                                    )
                                }
                                "MoviesApp+MVVM" -> {
                                    context.startActivity(
                                        MoviesHomeActivity.newIntent(context, false)
                                    )
                                }
                                "DatingApp" -> {
                                    context.startActivity(
                                        DatingHomeActivity.newIntent(context, false)
                                    )
                                }
                                "TikTok" -> {
                                    context.startActivity(
                                        TiktokActivity.newIntent(context)
                                    )
                                }
                                "Meditation" -> {
                                    context.startActivity(
                                        MeditationActivity.newIntent(context)
                                    )
                                }
                                else -> TODO("Create your activity to launch any new demo app")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(text = title, modifier = Modifier.padding(8.dp))
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun PreviewDemoUis() {
    DemoUIList()
}