package com.dreammkr.pokedex.domain

import com.dreammkr.pokedex.data.PokemonRepository
import com.dreammkr.pokedex.domain.model.Pokemon
import javax.inject.Inject

class GetRandomPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(): Pokemon? {
        val pokemons = repository.getAllPokemonFromDatabase()
        if (!pokemons.isNullOrEmpty()) {
            return pokemons[(pokemons.indices).random()]
        }
        return null
    }
}