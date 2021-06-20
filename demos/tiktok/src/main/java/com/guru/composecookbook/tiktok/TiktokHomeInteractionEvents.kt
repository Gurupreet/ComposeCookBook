package com.guru.composecookbook.tiktok

import com.guru.composecookbook.data.model.Album

sealed class TiktokHomeInteractionEvents {
    data class OpenProfile(val album: Album) : TiktokHomeInteractionEvents()
    data class ShareVideo(val album: Album) : TiktokHomeInteractionEvents()
    object OpenComments : TiktokHomeInteractionEvents()
}