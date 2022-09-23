package com.pokedex.domain

import com.pokedex.data.PokemonRepository
import com.pokedex.data.model.PokemonModel

class GetPokemonUseCase {

    private val repository = PokemonRepository()

    suspend operator fun invoke(id: Int): PokemonModel? = repository.getPokemon(id)

}