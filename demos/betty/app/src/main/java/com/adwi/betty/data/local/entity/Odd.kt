package com.adwi.betty.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adwi.betty.ui.main.Range

@Entity(tableName = "odd_table")
data class Odd(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sportKey: String = "",
    val sportName: String = "",
    val commenceTime: String = "",
    val bookmakerKey: String = "",
    val bookmakerTitle: String = "",
    val bookmakerLastUpdate: String = "",
    val marketId: String = "",
    val teamAName: String = "",
    val teamAPrice: Double = 0.0,
    val teamBName: String = "",
    val teamBPrice: Double = 0.0,
    val difference: Double = 0.0,
    val range: Range = Range.All,
    val hasBetOn: Boolean = false
) {
    companion object {
        val mock =
            Odd(
                sportKey = "soccer_epl",
                sportName = "Football",
                commenceTime = "2020/09/20 18.00",
                bookmakerKey = "williams_hill",
                bookmakerTitle = "Williams Hill",
                bookmakerLastUpdate = "2020/03/21 16.00",
                marketId = "h2h",
                teamAName = "Man U",
                teamAPrice = 1.30,
                teamBName = "Lech Poznan",
                teamBPrice = 15.0,
                difference = 13.7,
                range = Range.All,
                hasBetOn = true
            )

        val mockList = listOf(mock, mock, mock)
    }
}


