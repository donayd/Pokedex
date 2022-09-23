package com.pokedex.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokedex.data.model.PokemonModel
import com.pokedex.domain.GetPokemonUseCase
import com.pokedex.domain.GetRandomPokemonUseCase
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    val pokemonModel = MutableLiveData<PokemonModel?>()
    val isLoading = MutableLiveData<Boolean>()

    var getPokemonUseCase = GetPokemonUseCase()
    var getRandomPokemonUseCase = GetRandomPokemonUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            pokemonModel.postValue(getPokemonUseCase(25))
            isLoading.postValue(false)
        }
    }

    fun randomPokemon() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val pokemon = getRandomPokemonUseCase()
            pokemonModel.postValue(pokemon)
            isLoading.postValue(false)
        }
    }

}