package com.guru.composecookbook.ui.home.sharedelement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity

/**
 * Demonstrates Compose shared-element transitions. A [SharedTransitionLayout] wraps an
 * [AnimatedContent] that swaps between a list and a detail screen; the album art and title share a
 * key across both, so they smoothly grow/move between the row and the detail header
 * (`sharedElement`), while the surrounding card uses `sharedBounds`.
 */
class SharedElementActivity : ComponentActivity() {

  private val isDarkTheme: Boolean by lazy {
    intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { ComposeCookBookMaterial3Theme(isDarkTheme) { SharedElementDemo() } }
  }

  companion object {
    fun newIntent(context: Context, isDarkTheme: Boolean) =
      Intent(context, SharedElementActivity::class.java).apply {
        putExtra(DynamicUIActivity.DARK_THEME, isDarkTheme)
      }
  }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementDemo() {
  val albums = remember { AlbumsDataProvider.albums }
  var selected by remember { mutableStateOf<Album?>(null) }

  Scaffold { padding ->
    SharedTransitionLayout(modifier = Modifier.fillMaxSize().padding(padding)) {
      AnimatedContent(
        targetState = selected,
        label = "shared-element",
        transitionSpec = { fadeIn() togetherWith fadeOut() },
      ) { current ->
        if (current == null) {
          AlbumList(
            albums = albums,
            sharedScope = this@SharedTransitionLayout,
            animatedScope = this@AnimatedContent,
            onSelect = { selected = it },
          )
        } else {
          AlbumDetail(
            album = current,
            sharedScope = this@SharedTransitionLayout,
            animatedScope = this@AnimatedContent,
            onBack = { selected = null },
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun AlbumList(
  albums: List<Album>,
  sharedScope: SharedTransitionScope,
  animatedScope: AnimatedContentScope,
  onSelect: (Album) -> Unit,
) {
  LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(albums, key = { it.id }) { album ->
      Card(
        modifier =
          Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).clickable {
            onSelect(album)
          }
      ) {
        Row(modifier = Modifier.padding(12.dp)) {
          AlbumArt(album, sharedScope, animatedScope, Modifier.size(72.dp))
          Column(modifier = Modifier.padding(start = 12.dp)) {
            AlbumTitle(album, sharedScope, animatedScope, MaterialTheme.typography.titleMedium)
            Text(album.artist, style = MaterialTheme.typography.bodyMedium)
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun AlbumDetail(
  album: Album,
  sharedScope: SharedTransitionScope,
  animatedScope: AnimatedContentScope,
  onBack: () -> Unit,
) {
  Column(modifier = Modifier.fillMaxSize().clickable { onBack() }) {
    AlbumArt(
      album,
      sharedScope,
      animatedScope,
      Modifier.fillMaxWidth().height(280.dp),
      rounded = false,
    )
    Column(modifier = Modifier.padding(16.dp)) {
      AlbumTitle(album, sharedScope, animatedScope, MaterialTheme.typography.headlineMedium)
      Text(album.artist, style = MaterialTheme.typography.titleMedium)
      Text(
        album.descriptions,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 12.dp),
      )
      Text(
        "Tap anywhere to go back",
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(top = 24.dp),
      )
    }
  }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun AlbumArt(
  album: Album,
  sharedScope: SharedTransitionScope,
  animatedScope: AnimatedVisibilityScope,
  modifier: Modifier = Modifier,
  rounded: Boolean = true,
) {
  with(sharedScope) {
    Image(
      painter = painterResource(id = album.imageId),
      contentDescription = album.song,
      contentScale = ContentScale.Crop,
      modifier =
        modifier
          .sharedElement(
            rememberSharedContentState(key = "image-${album.id}"),
            animatedVisibilityScope = animatedScope,
          )
          .clip(RoundedCornerShape(if (rounded) 12.dp else 0.dp)),
    )
  }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun AlbumTitle(
  album: Album,
  sharedScope: SharedTransitionScope,
  animatedScope: AnimatedVisibilityScope,
  style: androidx.compose.ui.text.TextStyle,
) {
  with(sharedScope) {
    Text(
      text = album.song,
      style = style,
      modifier =
        Modifier.sharedElement(
          rememberSharedContentState(key = "title-${album.id}"),
          animatedVisibilityScope = animatedScope,
        ),
    )
  }
}
