package com.adwi.betty.fake

import com.adwi.betty.data.local.entity.Odd
import com.adwi.betty.data.repository.OddsRepositoryInterface
import com.adwi.betty.ui.main.Range
import com.adwi.betty.util.Constants
import com.adwi.betty.util.round
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRepository : OddsRepositoryInterface {

    private val odds = mutableListOf<Odd>()
    private val oddsFlow = MutableStateFlow<List<Odd>>(odds)

    override suspend fun fetchOddsRemote(sportKey: String) {}

    fun insertOdd(odd: Odd) {
        odds.add(odd)
    }

    override fun getOddsLocal(): Flow<List<Odd>> {
        return oddsFlow
    }

    override suspend fun updateOdd(odd: Odd) {
        odds.add(odd)
        refreshData()
    }

    override suspend fun deleteAllOdds() {
        odds.clear()
        refreshData()
    }

    override suspend fun deleteOddsOlderThan(timeMillis: Long) {
        odds.forEach { odd ->
            odd.commenceTime < timeMillis.toString()
            odds.remove(odd)
        }
        refreshData()
    }

    override fun workoutDifference(teamA: Double, teamB: Double): Double {
        var subtraction = teamA.minus(teamB)
        if (subtraction < 0) subtraction = -subtraction
        return subtraction.round(2)
    }

    override fun setRangeFromDifference(difference: Double): Range = when (difference) {
        in Constants.MIN_ODD_DIFFERENCE..(Constants.MIN_ODD_DIFFERENCE + 3.0) -> Range.LOW
        in (Constants.MIN_ODD_DIFFERENCE + 3.0)..(Constants.MIN_ODD_DIFFERENCE + 6.0) -> Range.MEDIUM
        else -> Range.HIGH
    }

    override fun isItGoodOdd(odd: Odd): Boolean {
        val difference = workoutDifference(odd.teamAPrice, odd.teamBPrice)
        return difference > Constants.MIN_ODD_DIFFERENCE
    }

    fun refreshData() {
        oddsFlow.value = odds
    }
}