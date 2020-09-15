package com.guru.composecookbook.ui.cryptoappmvvm.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.cryptoappmvvm.Models.Crypto
import com.guru.composecookbook.ui.cryptoappmvvm.ui.detail.CryptoDetailActivity

sealed class CryptoHomeEvents {
    data class AddedToFav(val crypto: Crypto) : CryptoHomeEvents()
    data class OpenDetailScreen(val crypto: Crypto) : CryptoHomeEvents()
}

class CryptoHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            ComposeCookBookTheme {
                val viewModel: CryptoHomeViewModel = viewModel()
                CryptoHomeScreen(
                    onCryptoHomeEvents = { cryptoHomeEvent ->
                        when (cryptoHomeEvent) {
                            is CryptoHomeEvents.AddedToFav -> {
                                viewModel.addToFav(cryptoHomeEvent.crypto)
                            }
                            is CryptoHomeEvents.OpenDetailScreen -> {
                                startActivity(
                                    CryptoDetailActivity.newIntent(
                                        this,
                                        cryptoHomeEvent.crypto
                                    )
                                )
                            }
                        }
                    }
                )
            }
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
        CryptoHomeScreen({})
    }
}