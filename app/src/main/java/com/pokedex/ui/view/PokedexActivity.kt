package com.pokedex.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pokedex.databinding.ActivityPokedexBinding
import com.pokedex.ui.adapter.PokemonsAdapter
import com.pokedex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokedexBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPokedexBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        pokemonViewModel.onCreate()
        initRecyclerView()

        pokemonViewModel.isLoading.observe(this) {
            binding.animationView.isVisible = it
        }


    }

    private fun initRecyclerView() {
        pokemonViewModel.pokemonList.observe(this) { pokemons ->
            val manger = LinearLayoutManager(this)
            val decoration = DividerItemDecoration(this, manger.orientation)
            binding.rvPokemons.layoutManager = manger
            binding.rvPokemons.adapter = PokemonsAdapter(pokemons)
            binding.rvPokemons.addItemDecoration(decoration)
        }
    }


}