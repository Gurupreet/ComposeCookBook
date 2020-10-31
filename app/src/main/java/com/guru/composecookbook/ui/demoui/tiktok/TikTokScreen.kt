package com.guru.composecookbook.ui.demoui.tiktok

sealed class TikTokScreen(val route: String) {
    object Home : TikTokScreen("Home")
    object Discover : TikTokScreen("Discover")
    object Create : TikTokScreen("Create")
    object Inbox : TikTokScreen("Inbox")
    object Me : TikTokScreen("Me")
}