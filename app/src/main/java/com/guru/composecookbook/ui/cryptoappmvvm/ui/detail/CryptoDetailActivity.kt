package com.guru.composecookbook.ui.cryptoappmvvm.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.cryptoappmvvm.data.CryptoDemoDataProvider
import com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities.Crypto

class CryptoDetailActivity : AppCompatActivity() {

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
    val crypto = CryptoDemoDataProvider.bitcoin
    ComposeCookBookTheme {
        CryptoDetailScreen(crypto) {

        }
    }
}