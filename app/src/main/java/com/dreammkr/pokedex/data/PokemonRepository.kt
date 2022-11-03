package com.dreammkr.pokedex.data

import com.dreammkr.pokedex.data.database.dao.PokemonDao
import com.dreammkr.pokedex.data.database.entities.PokemonEntity
import com.dreammkr.pokedex.data.model.PokemonModel
import com.dreammkr.pokedex.data.network.PokemonService
import com.dreammkr.pokedex.domain.model.Pokemon
import com.dreammkr.pokedex.domain.model.toDomain
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService,
    private val pokemonDao: PokemonDao
) {

    suspend fun getAllPokemonFromApi(): List<Pokemon> {
        val response: List<PokemonModel> = api.getAllPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun getAllPokemonFromDatabase(): List<Pokemon> {
        val response: List<PokemonEntity> = pokemonDao.getAllPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun insertPokemons(pokemons: List<PokemonEntity>) {
        pokemonDao.insertAllPokemon(pokemons)
    }

    suspend fun clearPokemons() {
        pokemonDao.deleteAllPokemons()
    }

}