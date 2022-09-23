package com.pokedex.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.pokedex.data.model.PokemonModel
import com.pokedex.data.model.PokemonProvider
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
            val result = getPokemonUseCase()

            if (!result.isNullOrEmpty()) {
                pokemonModel.postValue(result.first())
                isLoading.postValue(false)
            }
        }
    }

    fun randomPokemon() {
        isLoading.postValue(true)
        val pokemon = getRandomPokemonUseCase()
        pokemonModel.postValue(pokemon)
        isLoading.postValue(false)
    }

}