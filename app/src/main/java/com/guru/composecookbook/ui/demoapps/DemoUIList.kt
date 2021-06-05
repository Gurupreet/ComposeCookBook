package com.guru.composecookbook.ui.demoapps

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.cryptoapp.ui.home.CryptoHomeActivity
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.ui.demoapps.datingapp.DatingHomeActivity
import com.guru.composecookbook.moviesapp.ui.home.MoviesHomeActivity
import com.guru.composecookbook.ui.demoapps.spotify.SpotifyActivity
import com.guru.composecookbook.ui.demoapps.tiktok.TiktokActivity

@Composable
fun DemoUIList() {
    val demoUis = remember { DemoDataProvider.demoUiList }
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.semantics { testTag = "Demo UI List Screen" }
    ) {
        LazyColumn {
            items(
                count = demoUis.size,
                itemContent = { index ->
                    val title = demoUis[index]
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
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