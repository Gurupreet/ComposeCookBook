package com.adwi.betty.util

import com.adwi.betty.BuildConfig


class Constants {
    companion object {


        const val BASE_URL = "https://api.the-odds-api.com/"
        const val API_KEY = BuildConfig.BETTY_API_KEY
        const val BETTY_DATABASE = "wallpaper_database"

        const val MIN_ODD_DIFFERENCE = 3.0

        const val DEFAULT_BOOKMAKER = "William Hill"
    }
}