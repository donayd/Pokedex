package com.pokedex.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.dreammkr.pokedex.databinding.ActivityMainBinding
import com.pokedex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonViewModel.onCreate()

        pokemonViewModel.pokemonModel.observe(this) {
            binding.tvId.text = it.id.toString()
            binding.tvName.text = it.name
            binding.tvWeight.text = "Weight: ${it.weight} kg"
            binding.tvHeight.text = "Height: ${it.height} ft"

            Glide.with(this).load(it?.image).into(binding.ivPokemon)
        }

        pokemonViewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }

        binding.viewContainer.setOnClickListener { pokemonViewModel.randomPokemon() }

    }
}