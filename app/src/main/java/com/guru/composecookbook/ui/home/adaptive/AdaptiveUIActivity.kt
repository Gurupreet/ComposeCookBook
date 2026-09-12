package com.guru.composecookbook.ui.home.adaptive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity

/**
 * Demonstrates adaptive layouts for large screens:
 * - [NavigationSuiteScaffold] automatically switches between a bottom bar (compact), navigation
 *   rail (medium) and drawer (expanded) based on the window size class.
 * - [ListDetailPaneScaffold] shows a single pane on phones and a two-pane list/detail layout on
 *   tablets, foldables and desktops.
 */
class AdaptiveUIActivity : ComponentActivity() {

  private val isDarkTheme: Boolean by lazy {
    intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { ComposeCookBookMaterial3Theme(isDarkTheme) { AdaptiveDemoScreen() } }
  }

  companion object {
    fun newIntent(context: Context, isDarkTheme: Boolean) =
      Intent(context, AdaptiveUIActivity::class.java).apply {
        putExtra(DynamicUIActivity.DARK_THEME, isDarkTheme)
      }
  }
}

enum class AdaptiveDemoDestination(val label: String, val icon: ImageVector) {
  Albums("Albums", Icons.Default.Album),
  Favorites("Favorites", Icons.Default.Favorite),
  Profile("Profile", Icons.Default.Person),
}

@Composable
fun AdaptiveDemoScreen() {
  var currentDestination by remember { mutableStateOf(AdaptiveDemoDestination.Albums) }

  NavigationSuiteScaffold(
    navigationSuiteItems = {
      AdaptiveDemoDestination.entries.forEach { destination ->
        item(
          icon = { Icon(destination.icon, contentDescription = destination.label) },
          label = { Text(destination.label) },
          selected = destination == currentDestination,
          onClick = { currentDestination = destination },
        )
      }
    }
  ) {
    when (currentDestination) {
      AdaptiveDemoDestination.Albums -> AlbumListDetail()
      AdaptiveDemoDestination.Favorites -> WindowInfoPane("Favorites")
      AdaptiveDemoDestination.Profile -> WindowInfoPane("Profile")
    }
  }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AlbumListDetail() {
  val navigator = rememberListDetailPaneScaffoldNavigator<Album>()

  BackHandler(navigator.canNavigateBack()) { navigator.navigateBack() }

  ListDetailPaneScaffold(
    directive = navigator.scaffoldDirective,
    value = navigator.scaffoldValue,
    listPane = {
      AnimatedPane {
        LazyColumn {
          items(AlbumsDataProvider.albums) { album ->
            ListItem(
              modifier =
                Modifier.fillMaxWidth().clickable {
                  navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, album)
                },
              headlineContent = { Text(album.song) },
              supportingContent = { Text(album.artist) },
              leadingContent = { Icon(Icons.Default.Album, contentDescription = null) },
              trailingContent = { Text(album.genre, style = MaterialTheme.typography.labelSmall) },
              tonalElevation = 2.dp,
            )
            androidx.compose.material3.HorizontalDivider()
          }
        }
      }
    },
    detailPane = {
      AnimatedPane {
        val album = navigator.currentDestination?.content
        AlbumDetailPane(album)
      }
    },
  )
}

@Composable
private fun AlbumDetailPane(album: Album?) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    if (album == null) {
      Text(
        text = "Select an album from the list",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(16.dp),
      )
    } else {
      Card(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(24.dp)) {
          Text(text = album.song, style = MaterialTheme.typography.headlineMedium)
          Text(text = album.artist, style = MaterialTheme.typography.titleMedium)
          Text(
            text = album.descriptions,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp),
          )
        }
      }
    }
  }
}

@Composable
private fun WindowInfoPane(title: String) {
  val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = title, style = MaterialTheme.typography.headlineMedium)
      Text(
        text = "Width size class: ${windowSizeClass.windowWidthSizeClass}",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 8.dp),
      )
      Text(
        text = "Height size class: ${windowSizeClass.windowHeightSizeClass}",
        style = MaterialTheme.typography.bodyMedium,
      )
    }
  }
}
