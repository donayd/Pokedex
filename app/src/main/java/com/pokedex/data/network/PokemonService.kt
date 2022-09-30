package com.pokedex.data.network

import com.pokedex.data.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(
    private val api: PokemonApiClient
) {

    suspend fun getAllPokemons(): List<PokemonModel> {
        return withContext(Dispatchers.IO) {
            val pokemons: MutableList<PokemonModel> = mutableListOf()
            for (id in 1..150) pokemons.add(getPokemon(id)!!)
            pokemons
        }
    }

    suspend fun getPokemon(id: Int): PokemonModel? {
        return withContext(Dispatchers.IO) {
            api.getPokemon(id).body()
        }
    }

}