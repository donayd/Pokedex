package com.pokedex.ui.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.pokedex.R
import com.pokedex.databinding.FragmentDetailBinding
import com.pokedex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

const val POKEMON_ID = "pokemon_id"

private var pokemonId: Int = 1

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    private lateinit var images_url: List<String>
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

        val aninSor = AnimationUtils.loadAnimation(activity, R.anim.slide_out_right)
        val aninSol = AnimationUtils.loadAnimation(activity, R.anim.slide_out_left)
        val aninSir = AnimationUtils.loadAnimation(activity, R.anim.slide_in_right)
        val aninSil = AnimationUtils.loadAnimation(activity, R.anim.slide_in_left)

        val aninSou = AnimationUtils.loadAnimation(activity, R.anim.slide_out_up)
        val aninSod = AnimationUtils.loadAnimation(activity, R.anim.slide_out_down)
        val aninSiu = AnimationUtils.loadAnimation(activity, R.anim.slide_in_up)
        val aninSid = AnimationUtils.loadAnimation(activity, R.anim.slide_in_down)

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

            images_url = listOf(it.image_front, it.image_back)

            Glide.with(this).load(images_url[0]).into(binding.ivPokemon)
        }

        binding.btnDetail.setOnClickListener {
            Glide.with(this).load(
                if (flip) images_url[1] else images_url[0]
            ).into(binding.ivPokemon)
            flip = !flip
        }

        binding.btnUp.setOnClickListener {
            if(!binding.clImage.isVisible){
                binding.clImage.isVisible = true
                binding.clDetails.isVisible = false
                binding.clImage.startAnimation(aninSiu)
                binding.clDetails.startAnimation(aninSod)
            }
        }

        binding.btnDown.setOnClickListener {
            if(binding.clImage.isVisible) {
                binding.clImage.isVisible = false
                binding.clDetails.isVisible = true
                binding.clImage.startAnimation(aninSou)
                binding.clDetails.startAnimation(aninSid)
            }
        }

        binding.btnLeft.setOnClickListener {
            pokemonId = (pokemonId + 148) % 150 + 1
            pokemonViewModel.getPokemon(pokemonId)
            if (binding.clImage.isVisible) {
                binding.clImage.startAnimation(aninSil)
            } else {
                binding.clDetails.startAnimation(aninSil)
            }
        }

        binding.btnRight.setOnClickListener {
            pokemonId = (pokemonId % 150) + 1
            pokemonViewModel.getPokemon(pokemonId)
            if (binding.clImage.isVisible) {
                binding.clImage.startAnimation(aninSir)
            } else {
                binding.clDetails.startAnimation(aninSir)
            }
        }

    }


}