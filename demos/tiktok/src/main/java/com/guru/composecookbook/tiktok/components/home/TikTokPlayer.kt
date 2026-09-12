package com.guru.composecookbook.tiktok.components.home

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun TikTokPlayer(context: Context, url: String, selected: Boolean) {
  val tiktokPlayer = remember {
    ExoPlayer.Builder(context).build().apply {
      setMediaItem(MediaItem.fromUri("asset:///$url"))
      prepare()
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

  DisposableEffect(key1 = url) { onDispose { tiktokPlayer.release() } }
}
