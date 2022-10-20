package com.pokedex.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokedex.domain.GetPokemonsUseCase
import com.pokedex.domain.GetRandomPokemonUseCase
import com.pokedex.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getRandomPokemonUseCase: GetRandomPokemonUseCase
) : ViewModel() {

    val pokemonModel = MutableLiveData<Pokemon>()
    val pokemonList = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val pokemons = getPokemonsUseCase()

            if (!pokemons.isNullOrEmpty()) {
                pokemonList.postValue(pokemons)
                pokemonModel.postValue(pokemons.first())
                isLoading.postValue(false)
            }
        }
    }

    fun getPokemon(id: Int) {
        viewModelScope.launch {
            val pokemons = getPokemonsUseCase()
            if (!pokemons.isNullOrEmpty()) {
                val pokemon = pokemons.find { it.id == id }
                pokemonModel.postValue(pokemon!!)
            }
        }
    }

    fun randomPokemon() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val pokemon = getRandomPokemonUseCase()
            pokemonModel.postValue(pokemon!!)
            isLoading.postValue(false)
        }
    }

}