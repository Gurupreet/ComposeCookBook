package com.guru.composecookbook

import android.app.Application
import android.content.Context

/**
 * Custom Application class for initializing global application state.
 */
class App : Application() {

    /**
     * Initializes the singleton instance of the App class and ensuring it is non-null.
     */
    init {
        instance = requireNotNull(this)
    }

    companion object {
        // Singleton instance of the App class
        private lateinit var instance: App

        /**
         * Provides the application context.
         *
         * @return Context of the application.
         */
        fun applicationContext(): Context {
            return instance
        }
    }

}