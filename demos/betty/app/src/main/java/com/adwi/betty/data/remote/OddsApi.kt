package com.adwi.betty.data.remote

import com.adwi.betty.data.remote.dto.MatchDto
import com.adwi.betty.data.remote.dto.SportKeyDto
import com.adwi.betty.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OddsApi {

    /*
    * Ods Api doc
    * https://the-odds-api.com/liveapi/guides/v4/#endpoint-2
    */

    @GET("v4/sports/soccer_epl/odds/")
    suspend fun getOddsUkPremierLeague(
        @Query("regions") regions: String = "uk", // Valid regions: us, uk, au, eu
        @Query("markets") markets: String = "h2h", // Valid regions: h2h, spreads, totals, outrights
        @Query("apiKey") apiKey: String = API_KEY
    ): List<MatchDto>



    @GET("v4/sports/{sportKey}/odds/")
    suspend fun getOddsBySportKey(
        @Path(value = "sportKey", encoded = true) sportKey: String,
        @Query("regions") regions: String = "uk", // Valid regions: us, uk, au, eu
        @Query("markets") markets: String = "h2h", // Valid regions: h2h, spreads, totals, outrights
        @Query("apiKey") apiKey: String = API_KEY
    ): List<MatchDto>

    @GET("v4/sports/")
    suspend fun getSportKeys(
        @Query("apiKey") apiKey: String = API_KEY
    ): List<SportKeyDto>
}