package com.guru.composecookbook.cryptoapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto

@Dao
@TypeConverters()
interface CryptoDao {

    @Transaction
    @Query("select * from crypto_fav_table")
    fun getFavCryptos(): LiveData<List<Crypto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFav(favCrypto: Crypto)

    @Delete
    fun removeFav(crypto: Crypto)

}