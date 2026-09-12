package com.guru.composecookbook.tiktok

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation routes for the TikTok demo. Each screen is a serializable route; [route] is
 * kept as the human readable label shown in the bottom bar.
 */
sealed interface TikTokScreen {
  val route: String

  @Serializable
  data object Home : TikTokScreen {
    override val route = "Home"
  }

  @Serializable
  data object Discover : TikTokScreen {
    override val route = "Discover"
  }

  @Serializable
  data object Create : TikTokScreen {
    override val route = "Create"
  }

  @Serializable
  data object Inbox : TikTokScreen {
    override val route = "Inbox"
  }

  @Serializable
  data object Me : TikTokScreen {
    override val route = "Me"
  }

  @Serializable
  data class Profile(val userId: String) : TikTokScreen {
    override val route = "Profile"
  }
}
