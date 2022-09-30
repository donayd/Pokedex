package com.pokedex.data

import com.pokedex.data.database.dao.PokemonDao
import com.pokedex.data.database.entities.PokemonEntity
import com.pokedex.data.model.PokemonModel
import com.pokedex.data.network.PokemonService
import com.pokedex.domain.model.Pokemon
import com.pokedex.domain.model.toDomain
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