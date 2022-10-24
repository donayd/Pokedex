package com.pokedex.ui.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.ads.AdRequest
import com.pokedex.R
import com.pokedex.core.animateThis
import com.pokedex.core.load
import com.pokedex.databinding.FragmentDetailBinding
import com.pokedex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

const val POKEMON_ID = "pokemon_id"

private var pokemonId: Int = 1

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    private lateinit var imagesUrl: List<String>
    private var flip = true

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

            binding.tvName.text = it.name
            binding.tvHeight.text = "HT:\n${it.height / 10.0}m"
            binding.tvWeight.text = "WT:\n${it.weight / 10.0}kg"
            binding.tvHp.text = "HP:\n${it.hp}"
            binding.tvAtk.text = "ATK:\n${it.attack}"
            binding.tvDef.text = "DEF:\n${it.defense}"
            binding.tvType.text = "Type:\n${it.types}"

            imagesUrl = listOf(it.image_front, it.image_back)
            binding.ivPokemon.load(imagesUrl[0])
        }

        binding.btnDetail.setOnClickListener {
            binding.ivPokemon.load(imagesUrl[if (flip) 1 else 0])
            flip = !flip
        }

        binding.btnUp.setOnClickListener {
            if (!binding.clImage.isVisible) {
                binding.clImage.isVisible = true
                binding.clDetails.isVisible = false
                binding.clImage.animateThis(R.anim.slide_in_up)
                binding.clDetails.animateThis(R.anim.slide_out_down)
            }
        }

        binding.btnDown.setOnClickListener {
            if (binding.clImage.isVisible) {
                binding.clImage.isVisible = false
                binding.clDetails.isVisible = true
                binding.clImage.animateThis(R.anim.slide_out_up)
                binding.clDetails.animateThis(R.anim.slide_in_down)
            }
        }

        binding.btnLeft.setOnClickListener {
            pokemonId = (pokemonId + 148) % 150 + 1
            pokemonViewModel.getPokemon(pokemonId)
            if (binding.clImage.isVisible) {
                binding.clImage.animateThis(R.anim.slide_in_left)
            } else {
                binding.clDetails.animateThis(R.anim.slide_in_left)
            }
        }

        binding.btnRight.setOnClickListener {
            pokemonId = (pokemonId % 150) + 1
            pokemonViewModel.getPokemon(pokemonId)
            if (binding.clImage.isVisible) {
                binding.clImage.animateThis(R.anim.slide_in_right)
            } else {
                binding.clDetails.animateThis(R.anim.slide_in_right)
            }
        }

    }


}