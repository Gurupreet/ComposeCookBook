package com.adwi.betty.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.adwi.betty.data.local.BettyDatabase
import com.adwi.betty.data.local.entity.Odd
import com.adwi.betty.data.remote.OddsApi
import com.adwi.betty.ui.main.Range
import com.adwi.betty.util.Constants.Companion.DEFAULT_BOOKMAKER
import com.adwi.betty.util.Constants.Companion.MIN_ODD_DIFFERENCE
import com.adwi.betty.util.round
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "OddsRepository"

class OddsRepository @Inject constructor(
    private val api: OddsApi,
    private val database: BettyDatabase
) : OddsRepositoryInterface {

    private val oddsDao = database.oddsDao()

    override suspend fun fetchOddsRemote(sportKey: String) {

        val goodOddList = mutableListOf<Odd>()

        try {
            val list = api.getOddsBySportKey(sportKey)

            list.forEach { match ->
                match.bookmakers.forEach { bookmaker ->
                    bookmaker.markets.forEach { market ->
                        val odd = Odd(
                            sportKey = match.sportKey,
                            sportName = match.sportName,
                            commenceTime = match.commenceTime,
                            bookmakerKey = bookmaker.key,
                            bookmakerTitle = bookmaker.title,
                            bookmakerLastUpdate = bookmaker.lastUpdate,
                            marketId = market.key,
                            teamAName = market.outcomes[0].name,
                            teamAPrice = market.outcomes[0].price,
                            teamBName = market.outcomes[1].name,
                            teamBPrice = market.outcomes[1].price,
                            difference = workoutDifference(
                                market.outcomes[0].price,
                                market.outcomes[1].price
                            ),
                            range = setRangeFromDifference(
                                workoutDifference(
                                    market.outcomes[0].price,
                                    market.outcomes[1].price
                                )
                            )
                        )
                        if (odd.bookmakerTitle == DEFAULT_BOOKMAKER && odd.difference > MIN_ODD_DIFFERENCE) {
                            goodOddList += odd
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }

        database.withTransaction {
            oddsDao.insertOdds(goodOddList)
        }
    }

    override fun getOddsLocal(): Flow<List<Odd>> = oddsDao.getAllOdds()

    override suspend fun updateOdd(odd: Odd) = oddsDao.updateOdd(odd)

    override suspend fun deleteOddsOlderThan(timeMillis: Long) =
        oddsDao.deleteOddsWithNoBetsOlderThan(timeMillis)

    override suspend fun deleteAllOdds() = oddsDao.deleteAllOdds()

    override fun setRangeFromDifference(difference: Double): Range = when (difference) {
        in MIN_ODD_DIFFERENCE..(MIN_ODD_DIFFERENCE + 3.0) -> Range.LOW
        in (MIN_ODD_DIFFERENCE + 3.0)..(MIN_ODD_DIFFERENCE + 6.0) -> Range.MEDIUM
        else -> Range.HIGH
    }

    override fun workoutDifference(teamA: Double, teamB: Double): Double {
        var subtraction = teamA.minus(teamB)
        if (subtraction < 0) subtraction = -subtraction
        return subtraction.round(2)
    }

    override fun isItGoodOdd(odd: Odd): Boolean {
        val difference = workoutDifference(odd.teamAPrice, odd.teamBPrice)
        return difference > MIN_ODD_DIFFERENCE
    }
}

val fullSportList = listOf(
    "soccer_argentina_primera_division",
    "soccer_belgium_first_div",
    "soccer_brazil_campeonato",
    "soccer_brazil_campeonato",
    "soccer_denmark_superliga",
    "soccer_efl_champ",
    "soccer_england_league1",
    "soccer_england_league2",
    "soccer_epl",
    "soccer_finland_veikkausliiga",
    "soccer_france_ligue_one",
    "soccer_france_ligue_two",
    "soccer_germany_bundesliga",
    "soccer_germany_bundesliga2",
    "soccer_italy_serie_a",
    "soccer_italy_serie_b",
    "soccer_japan_j_league",
    "soccer_korea_kleague1",
    "soccer_league_of_ireland",
    "soccer_mexico_ligamx",
    "soccer_netherlands_eredivisie",
    "soccer_norway_eliteserien",
    "soccer_portugal_primeira_liga",
    "soccer_russia_premier_league",
    "soccer_spain_la_liga",
    "soccer_spain_segunda_division",
    "soccer_spl",
    "soccer_sweden_allsvenskan",
    "soccer_sweden_superettan",
    "soccer_turkey_super_league",
    "soccer_uefa_champs_league",
    "soccer_uefa_europa_league",
    "soccer_usa_mls",
    "americanfootball_ncaaf",
    "americanfootball_nfl",
    "americanfootball_nfl_super_bowl_winner",
    "aussierules_afl",
    "rugbyleague_nrl",
    "baseball_mlb",
    "basketball_nba",
    "cricket_ipl",
    "cricket_odi",
    "cricket_test_match",
    "golf_masters_tournament_winner",
    "golf_pga_championship_winner",
    "golf_us_open_winner",
    "icehockey_nhl",
    "mma_mixed_martial_arts"
)