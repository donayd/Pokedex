package com.pokedex.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.pokedex.R
import com.pokedex.core.animateThis
import com.pokedex.core.toggleVisibility
import com.pokedex.databinding.FragmentCatchBinding
import com.pokedex.domain.model.Pokemon
import com.pokedex.ui.adapter.PokemonsAdapter
import com.pokedex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatchFragment : Fragment() {

    private lateinit var binding: FragmentCatchBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    private lateinit var pokAdapter: PokemonsAdapter
    private lateinit var pokemonList: List<Pokemon>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonViewModel.onCreate()

        initRecyclerView()
        initLoadAds()
        initListeners()
    }

    private fun initRecyclerView() {
        pokemonViewModel.pokemonList.observe(viewLifecycleOwner) { pokemons ->
            pokemonList = pokemons
            pokAdapter = PokemonsAdapter(pokemonList) { id, sharedView ->
                onItemSelected(id, sharedView)
            }

            binding.rvPokemons.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = pokAdapter
            }

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
                    binding.lyInfo.isVisible = pokemonList.isEmpty()
                    return false
                }
            })

        }
    }

    private fun onItemSelected(id: Int, sharedView: View) {
        // val extras = FragmentNavigatorExtras(sharedView to "pokemon_$id")
        findNavController().navigate(
            R.id.action_catchFragment_to_detailFragment,
            bundleOf(POKEMON_ID to id),
            null,
            null
        )
        binding.svPokemonName.setQuery("", false)
        binding.svPokemonName.clearFocus()
    }

    private fun initLoadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
    }

    private fun initListeners() {
        binding.tvName.animateThis(R.anim.zoom_in)

        pokemonViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.lyLoading.isVisible = it
        }

        binding.btnSearch.setOnClickListener {
            binding.svPokemonName.toggleVisibility()
            binding.svPokemonName.animateThis(
                if (!binding.svPokemonName.isVisible) R.anim.fade_out else R.anim.fade_in
            )
        }

        binding.svPokemonName.setOnCloseListener {
            binding.svPokemonName.clearFocus()
            binding.svPokemonName.visibility = View.GONE
            binding.svPokemonName.animateThis(R.anim.fade_out)
            false
        }
    }

}