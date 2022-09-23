package com.pokedex.domain

import com.pokedex.data.PokemonRepository
import com.pokedex.data.model.PokemonModel

class GetRandomPokemonUseCase {

    private val repository = PokemonRepository()

    suspend operator fun invoke(): PokemonModel? = repository.getPokemon((1..150).random())

}