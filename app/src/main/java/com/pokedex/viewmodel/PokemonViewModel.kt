package com.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokedex.model.PokemonModel
import com.pokedex.model.PokemonProvider

class PokemonViewModel : ViewModel() {

    val pokemonModel: MutableLiveData<PokemonModel>()

    fun randomPokemon() {
        val currentPokemon = PokemonProvider.random()
        pokemonModel.postValue(currentPokemon)
    }

}