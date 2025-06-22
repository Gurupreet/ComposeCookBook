package com.guru.composecookbook.cryptoapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import com.guru.composecookbook.cryptoapp.data.db.models.Crypto

@Dao
@TypeConverters()
interface CryptoDao {

  @Transaction @Query("select * from crypto_fav_table") fun getFavCryptos(): LiveData<List<Crypto>>

  @Insert(onConflict = OnConflictStrategy.IGNORE) fun addFav(favCrypto: Crypto)

  @Delete fun removeFav(crypto: Crypto)
}
