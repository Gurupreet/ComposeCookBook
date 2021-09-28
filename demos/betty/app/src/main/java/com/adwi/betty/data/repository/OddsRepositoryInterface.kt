package com.adwi.betty.data.repository

import com.adwi.betty.data.local.entity.Odd
import com.adwi.betty.ui.main.Range
import kotlinx.coroutines.flow.Flow

interface OddsRepositoryInterface {

    suspend fun fetchOddsRemote(sportKey: String)

    fun getOddsLocal(): Flow<List<Odd>>

    suspend fun updateOdd(odd: Odd)

    suspend fun deleteAllOdds()
    suspend fun deleteOddsOlderThan(timeMillis: Long)

    fun workoutDifference(teamA: Double, teamB: Double): Double
    fun setRangeFromDifference(difference: Double): Range
    fun isItGoodOdd(odd: Odd): Boolean
}