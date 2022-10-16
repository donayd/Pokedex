package com.pokedex.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pokedex.databinding.ActivityMainBinding
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

        var id = intent.getIntExtra("ID", 24)
        pokemonViewModel.getPokemon(id)

        pokemonViewModel.pokemonModel.observe(this) {
            binding.tvId.text = it.id.toString()
            binding.tvName.text = it.name
            binding.tvWeight.text = "Weight: ${it.weight / 10.0}kg"
            binding.tvHeight.text = "Height: ${it.height / 10.0}m"

            Glide.with(this).load(it?.image).into(binding.ivPokemon)

        }

        binding.viewContainer.setOnClickListener { pokemonViewModel.getPokemon(id++) }

    }
}