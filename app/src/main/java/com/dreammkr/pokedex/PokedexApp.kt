package com.dreammkr.pokedex

import android.app.Application
import com.dreammkr.pokedex.core.RemoteConfig
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokedexApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        RemoteConfig.init()
    }
}