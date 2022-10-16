package com.pokedex.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.pokedex.R
import com.pokedex.databinding.ActivityPokedexBinding
import com.pokedex.domain.model.Pokemon
import com.pokedex.ui.adapter.PokemonsAdapter
import com.pokedex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokedexActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokedexBinding
    private lateinit var pokAdapter: PokemonsAdapter
    private lateinit var pokemonList: List<Pokemon>

    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPokedexBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        pokemonViewModel.onCreate()

        initRecyclerView()
        initLoadAds()
        initListeners()
    }

    private fun initListeners() {
        val aninFi = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val aninFo = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        val aninZi = AnimationUtils.loadAnimation(this, R.anim.zoom_in)

        Glide.with(this).load(getString(R.string.PikachuUrl)).into(binding.ivPokemon)

        binding.tvName.startAnimation(aninZi)

        pokemonViewModel.isLoading.observe(this) {
            binding.animationView.isVisible = it
        }

        binding.btnSearch.setOnClickListener {
            val isVisible = binding.svPokemonName.isVisible
            binding.svPokemonName.isVisible = !isVisible
            binding.svPokemonName.startAnimation(if (isVisible) aninFo else aninFi)
        }

        binding.svPokemonName.setOnCloseListener {
            binding.svPokemonName.clearFocus()
            binding.svPokemonName.isVisible = false
            binding.svPokemonName.startAnimation(aninFo)
            false
        }
    }

    private fun initLoadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
    }

    private fun initRecyclerView() {
        pokemonViewModel.pokemonList.observe(this) { pokemons ->
            pokemonList = pokemons
            pokAdapter = PokemonsAdapter(pokemonList) { id, sharedView ->
                onItemSelected(id, sharedView)
            }
            binding.rvPokemons.layoutManager = GridLayoutManager(this, 2)
            binding.rvPokemons.adapter = pokAdapter

            binding.svPokemonName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding.svPokemonName.clearFocus()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    pokemonList = pokemons.filter { pokemons ->
                        pokemons.name.lowercase().contains(newText!!.lowercase())
                    }
                    pokAdapter.updatePokemons(pokemonList)
                    binding.itemNotFound.isVisible = pokemonList.isEmpty()
                    return false
                }
            })

        }
    }

    private fun onItemSelected(id: Int, sharedView: View) {
        val intent = Intent(this, MainActivity::class.java)
        Log.i("Pokemon", id.toString())
        intent.putExtra("ID", id)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, sharedView, "img_pokemon"
        )
        startActivity(intent, options.toBundle())
    }


}