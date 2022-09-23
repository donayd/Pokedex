package com.pokedex.data

import com.pokedex.data.model.PokemonModel
import com.pokedex.data.model.PokemonProvider
import com.pokedex.data.network.PokemonService

class PokemonRepository {

    private val api = PokemonService()

    suspend fun getAllPokemon(): List<PokemonModel> {
        val response = api.getPokemon()
        PokemonProvider.pokemons = response
        return response
    }

}