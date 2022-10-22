package com.pokedex

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.pokedex.core.RemoteConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokedexApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        RemoteConfig.init()
    }
}