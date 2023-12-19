package com.dreammkr.pokedex.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*if (RemoteConfig.getLastVersion() != BuildConfig.VERSION_NAME)
            toast(getString(R.string.update_app))*/

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}