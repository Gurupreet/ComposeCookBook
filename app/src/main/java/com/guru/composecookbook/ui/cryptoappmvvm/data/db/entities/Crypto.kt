package com.guru.composecookbook.ui.cryptoappmvvm.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "crypto_fav_table")
data class Crypto(
    @PrimaryKey
    val symbol: String,
    val price: Double,
    val name: String,
    val image: String,
    val dailyChange: Double,
    val dailyChangePercentage: Double,
    val high: Double,
    val low: Double,
    val marketCap: Long,
    val volume: Long,
    val supply: Long
) : Serializable {

    override fun equals(other: Any?): Boolean {
        return  (other as Crypto).symbol == symbol
    }

}