package com.pokedex.model

class PokemonProvider {
    companion object {

        fun random(): PokemonModel {
            val position = (0..6).random()
            return pokemons[position]
        }

        private val pokemons = listOf<PokemonModel>(
            PokemonModel("pikkachu", "url"),
            PokemonModel("pikkachu 1", "url"),
            PokemonModel("pikkachu 2", "url"),
            PokemonModel("pikkachu 3", "url"),
            PokemonModel("pikkachu 4", "url"),
            PokemonModel("pikkachu 5", "url"),
            PokemonModel("pikkachu 6", "url"),
        )
    }
}