package com.pokedex.data.model

class PokemonProvider {
    companion object {
        var pokemon: PokemonModel? = null
        var pokemons: List<PokemonModel> = emptyList()
    }
}