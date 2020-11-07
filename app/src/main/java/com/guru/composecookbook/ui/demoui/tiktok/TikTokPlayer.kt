package com.guru.composecookbook.ui.demoui.tiktok

import android.content.Context
import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.runtime.onDispose
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.RepeatModeUtil
import com.google.android.exoplayer2.util.Util

@Composable
fun TikTokPlayer(context: Context, url: String, selected: Boolean) {
    val tiktokPlayer = remember {
        SimpleExoPlayer.Builder(context)
            .build()
            .apply {
                val mediaSource = ProgressiveMediaSource.Factory(
                    DefaultDataSourceFactory(context, "composeCookBook"))
                    .createMediaSource(Uri.parse("asset:///${url}"))
                this.prepare(mediaSource)
            }
    }
    tiktokPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    tiktokPlayer.repeatMode = RepeatModeUtil.REPEAT_TOGGLE_MODE_ONE
    AndroidView({
        PlayerView(it).apply {
            useController = false
            player = tiktokPlayer
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }
    })

    tiktokPlayer.playWhenReady = selected

    onDispose(callback = {
        tiktokPlayer.release()
    })

}