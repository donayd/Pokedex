package com.pokedex.data

import com.pokedex.data.model.PokemonModel
import com.pokedex.data.model.PokemonProvider
import com.pokedex.data.network.PokemonService

class PokemonRepository {

    private val api = PokemonService()

    suspend fun getPokemon(id: Int): PokemonModel? {
        val response = api.getPokemon(id)
        PokemonProvider.pokemon = response
        return response
    }

}