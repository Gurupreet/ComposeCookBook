package com.guru.composecookbook.ui.demoui.cryptoappmvvm.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.guru.composecookbook.ui.demoui.cryptoappmvvm.data.db.entities.Crypto

@Dao
interface CryptoDao {

    @Transaction
    @Query("select * from crypto_fav_table")
    fun getFavCryptos(): LiveData<List<Crypto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFav(favCrypto: Crypto)

    @Delete
    fun removeFav(crypto: Crypto)

}