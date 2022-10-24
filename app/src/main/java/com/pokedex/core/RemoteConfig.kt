package com.pokedex.core

import android.annotation.SuppressLint
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object RemoteConfig {

    private const val REMOTE_VERSION = "ReleaseVersion"

    @SuppressLint("StaticFieldLeak")
    private lateinit var remoteConfig: FirebaseRemoteConfig

    private var DEFAULTS: HashMap<String, Any> =
        hashMapOf(REMOTE_VERSION to "1.0")

    fun init() {
        remoteConfig = getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }

        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(DEFAULTS)
        }

        return remoteConfig
    }

    fun getLastVersion(): String = remoteConfig.getString(REMOTE_VERSION)

}