package com.guru.composecookbook.ui.demoui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.ui.demoui.cryptoappmvvm.ui.home.CryptoHomeActivity
import com.guru.composecookbook.ui.demoui.datingapp.DatingHomeActivity
import com.guru.composecookbook.ui.demoui.spotify.SpotifyActivity
import com.guru.composecookbook.ui.demoui.tiktok.TiktokActivity
import com.guru.composecookbook.ui.demoui.moviesappmvi.ui.home.MoviesHomeActivity

@Composable
fun DemoUIList() {
    val demoUis = remember { DemoDataProvider.demoUiList }
    val context = AmbientContext.current
    Scaffold(
        modifier = Modifier.semantics { testTag = "Demo UI List Screen" }
    ) {
        LazyColumn {
            items(
                items = demoUis,
                itemContent = { title ->
                    Button(
                        onClick = {
                            when (title) {
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
                                else -> {
                                    context.startActivity(
                                        DemoUIHostActivity.newIntent(context, title, false)
                                    )
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth().padding(12.dp)
                    ) {
                        Text(text = title, modifier = Modifier.padding(8.dp))
                    }
                })
        }
    }
}


@Preview
@Composable
fun PreviewDemoUis() {
    DemoUIList()
}