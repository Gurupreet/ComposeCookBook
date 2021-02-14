package com.guru.composecookbook.ui.demoapps.cryptoappmvvm.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.db.entities.Crypto
import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.ui.detail.CryptoDetailActivity

sealed class CryptoHomeInteractionEvents {
    data class AddedToFav(val crypto: Crypto) : CryptoHomeInteractionEvents()
    data class OpenDetailScreen(val crypto: Crypto) : CryptoHomeInteractionEvents()
    data class RemoveFav(val crypto: Crypto) : CryptoHomeInteractionEvents()
    object LoadMoreItems : CryptoHomeInteractionEvents()
}

class CryptoHomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            ComposeCookBookTheme {
                val viewModel: CryptoHomeViewModel = viewModel()
                CryptoHomeScreen(
                    onCryptoHomeInteractionEvents = { handleInteractionEvents(it, viewModel) }
                )
            }
        }
    }

    fun handleInteractionEvents(
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
                startActivity(
                    CryptoDetailActivity.newIntent(
                        this,
                        cryptoHomeInteractionEvents.crypto
                    )
                )
            }
            CryptoHomeInteractionEvents.LoadMoreItems -> viewModel.loadMoreCryptos()
        }
    }

    companion object {
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, CryptoHomeActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    ComposeCookBookTheme {
        CryptoHomeScreen()
    }
}