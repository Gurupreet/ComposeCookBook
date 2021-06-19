package com.guru.composecookbook.tiktok

sealed class TikTokScreen(val route: String) {
    object Home : TikTokScreen("Home")
    object Discover : TikTokScreen("Discover")
    object Create : TikTokScreen("Create")
    object Inbox : TikTokScreen("Inbox")
    object Me : TikTokScreen("Me")
    object Profile : TikTokScreen("Profile")
}