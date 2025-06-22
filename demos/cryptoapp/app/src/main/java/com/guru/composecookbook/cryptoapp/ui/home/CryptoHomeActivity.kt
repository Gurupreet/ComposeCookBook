package com.guru.composecookbook.cryptoapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto
import com.guru.composecookbook.cryptoapp.ui.detail.CryptoDetailActivity
import com.guru.composecookbook.cryptoapp.ui.home.components.CryptoHomeScreen
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.theme.ComposeCookBookTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

sealed class CryptoHomeInteractionEvents {
  data class AddedToFav(val crypto: Crypto) : CryptoHomeInteractionEvents()
  data class OpenDetailScreen(val crypto: Crypto) : CryptoHomeInteractionEvents()
  data class RemoveFav(val crypto: Crypto) : CryptoHomeInteractionEvents()
}

class CryptoHomeActivity : ComponentActivity() {
  @OptIn(ExperimentalCoroutinesApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    setContent {
      ComposeCookBookMaterial3Theme {
        val viewModel: CryptoHomeViewModel =
          viewModel(factory = CryptoHomeViewModelFactory(LocalContext.current))
        CryptoHomeScreen(onCryptoHomeInteractionEvents = { handleInteractionEvents(it, viewModel) })
      }
    }
  }

  private fun handleInteractionEvents(
    cryptoHomeInteractionEvents: CryptoHomeInteractionEvents,
    viewModel: CryptoHomeViewModel
  ) {
    when (cryptoHomeInteractionEvents) {
      is CryptoHomeInteractionEvents.AddedToFav -> {
        viewModel.addToFav(cryptoHomeInteractionEvents.crypto)
      }
      is CryptoHomeInteractionEvents.RemoveFav -> {
        viewModel.removeFav(cryptoHomeInteractionEvents.crypto)
      }
      is CryptoHomeInteractionEvents.OpenDetailScreen -> {
        startActivity(CryptoDetailActivity.newIntent(this, cryptoHomeInteractionEvents.crypto))
      }
    }
  }

  companion object {
    const val DARK_THEME = "darkTheme"
    fun newIntent(context: Context, isDarkTheme: Boolean) =
      Intent(context, CryptoHomeActivity::class.java).apply { putExtra(DARK_THEME, isDarkTheme) }
  }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
  ComposeCookBookTheme { CryptoHomeScreen() }
}
