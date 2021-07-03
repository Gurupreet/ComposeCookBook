package com.guru.composecookbook.tiktok.components.home

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

@Composable
fun TikTokPlayer(context: Context, url: String, selected: Boolean) {
    val tiktokPlayer = remember {
        SimpleExoPlayer.Builder(context)
            .build()
            .apply {
                val mediaSource = ProgressiveMediaSource
                    .Factory(
                        DefaultDataSourceFactory(context, "composeCookBook")
                    )
                    .createMediaSource(MediaItem.fromUri(Uri.parse("asset:///${url}")))

                this.setMediaSource(mediaSource, true)
                this.prepare()
            }
    }
    tiktokPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    tiktokPlayer.repeatMode = Player.REPEAT_MODE_ONE
    AndroidView({
        PlayerView(it).apply {
            useController = false
            player = tiktokPlayer
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }
    })

    tiktokPlayer.playWhenReady = selected

    DisposableEffect(key1 = url) {
        onDispose {
            tiktokPlayer.release()
        }
    }
}