package com.dreammkr.pokedex.domain

import com.dreammkr.pokedex.data.PokemonRepository
import com.dreammkr.pokedex.data.database.entities.toDatabase
import com.dreammkr.pokedex.domain.model.Pokemon
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(): List<Pokemon> {
        var pokemons = repository.getAllPokemonFromDatabase()
        if (pokemons.isNullOrEmpty()) {
            pokemons = repository.getAllPokemonFromApi()
            repository.clearPokemons()
            repository.insertPokemons(pokemons.map { it.toDatabase() })
        }
        return pokemons
    }
}