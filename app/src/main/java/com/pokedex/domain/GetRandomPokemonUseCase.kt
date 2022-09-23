package com.pokedex.domain

import com.pokedex.data.model.PokemonModel
import com.pokedex.data.model.PokemonProvider

class GetRandomPokemonUseCase {

    operator fun invoke(): PokemonModel? {
        val pokemons = PokemonProvider.pokemons
        if (!pokemons.isNullOrEmpty()) {
            val randomNumber = pokemons.indices.random()
            return pokemons[randomNumber]
        }
        return null
    }

}