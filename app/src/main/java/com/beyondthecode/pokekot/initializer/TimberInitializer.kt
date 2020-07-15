package com.beyondthecode.pokekot.initializer

import android.content.Context
import androidx.startup.Initializer
import androidx.viewbinding.BuildConfig
import timber.log.Timber

/**
 * Created by Elías Bellido on 7/15/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("TimberInitializer is initialized")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}