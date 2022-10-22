package com.pokedex.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pokedex.BuildConfig
import com.pokedex.core.RemoteConfig

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (RemoteConfig.getLastVersion() != BuildConfig.VERSION_NAME) {
            Toast.makeText(this, "Actualiza la App", Toast.LENGTH_SHORT).show()
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}