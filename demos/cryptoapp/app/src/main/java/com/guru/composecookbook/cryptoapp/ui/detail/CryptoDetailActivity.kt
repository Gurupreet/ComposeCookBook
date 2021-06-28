package com.guru.composecookbook.cryptoapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto
import com.guru.composecookbook.cryptoapp.ui.detail.components.CryptoDetailScreen
import com.guru.composecookbook.theme.ComposeCookBookTheme

class CryptoDetailActivity : ComponentActivity() {

    val crypto by lazy {
        intent.getSerializableExtra(CRYPTO) as Crypto
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            ComposeCookBookTheme {
                CryptoDetailScreen(crypto) {
                    onBackPressed()
                }
            }
        }
    }

    companion object {
        const val CRYPTO = "crypto"
        fun newIntent(context: Context, crypto: Crypto) =
            Intent(context, CryptoDetailActivity::class.java).apply {
                putExtra(CRYPTO, crypto)
            }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    val crypto = com.guru.composecookbook.cryptoapp.data.CryptoDemoDataProvider.bitcoin
    ComposeCookBookTheme {
        CryptoDetailScreen(crypto) {

        }
    }
}