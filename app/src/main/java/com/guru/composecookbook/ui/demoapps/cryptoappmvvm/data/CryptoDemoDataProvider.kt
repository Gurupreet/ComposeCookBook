package com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data

import com.guru.composecookbook.ui.demoapps.cryptoappmvvm.data.db.entities.Crypto

object CryptoDemoDataProvider {

    val bitcoin = Crypto(
        "BTC",
        1025.43,
        "Bitcoin",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/128px-Bitcoin.svg.png",
        12.1,
        1.2,
        1044.01,
        1000.09,
        1992922,
        12223333.0,
        120234333.99
    )

    val demoList = mutableListOf(bitcoin, bitcoin.copy(symbol = "ETH", name = "Ethereum"))


}