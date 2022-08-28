package com.pokedex.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pokedex.databinding.ActivityMainBinding
import com.pokedex.viewmodel.PokemonViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var pokemonViewModel: PokemonViewModel by ViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonViewModel.pokemonModel.observe(this, Observer {
            binding.tvName.text = it.name
            binding.tvUrl.text = it.url
        })

        binding.viewContainer.setOnClickListener{ pokemonViewModel.randomPokemon() }

    }
}