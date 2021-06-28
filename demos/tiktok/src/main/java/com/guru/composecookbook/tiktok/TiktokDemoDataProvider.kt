package com.guru.composecookbook.tiktok

import androidx.compose.ui.graphics.Color

object TiktokDemoDataProvider {
    val bottomBarList = listOf(
        TikTokScreen.Home,
        TikTokScreen.Discover,
        TikTokScreen.Create,
        TikTokScreen.Inbox,
        TikTokScreen.Me
    )

    val lanes =
        listOf(
            "OhHO ohNO",
            "FunFacts",
            "HappyDeepavli",
            "HalloweenIsHere",
            "BoomBoom",
            "No no no no"
        )

    val customGray = Color.LightGray.copy(alpha = 0.5f)

    val videos = listOf("t1.mp4", "t2.mp4", "t3.mp4")
}