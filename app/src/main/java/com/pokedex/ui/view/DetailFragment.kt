package com.pokedex.ui.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.pokedex.databinding.FragmentDetailBinding
import com.pokedex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

const val POKEMON_ID = "pokemon_id"

private var pokemonId: Int = 1

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        arguments?.let {
            pokemonId = it.getInt(POKEMON_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLoadAds()
        initListeners()
    }

    private fun initLoadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
    }

    private fun initListeners() {
        ViewCompat.setTransitionName(binding.ivPokemon, "pokemon_$pokemonId")
        pokemonViewModel.getPokemon(pokemonId)

        pokemonViewModel.pokemonModel.observe(viewLifecycleOwner) {
            binding.tvId.text = it.id.toString()
            binding.tvName.text = it.name
            binding.tvWeight.text = "Weight: ${it.weight / 10.0}kg"
            binding.tvHeight.text = "Height: ${it.height / 10.0}m"

            Glide.with(this).load(it?.image).into(binding.ivPokemon)
        }

        /*binding.viewContainer.setOnClickListener {
            pokemonId = (pokemonId) % 150 + 1
            pokemonViewModel.getPokemon(pokemonId)
        }*/
    }


}