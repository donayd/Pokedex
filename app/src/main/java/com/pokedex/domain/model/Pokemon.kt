package com.pokedex.domain.model

import com.pokedex.data.database.entities.PokemonEntity
import com.pokedex.data.model.PokemonModel

data class Pokemon(
    val id: Int,
    val name: String,
    val image: String,
    val height: Int,
    val weight: Int
)

fun PokemonModel.toDomain() = Pokemon(id, name, image.url, height, weight)
fun PokemonEntity.toDomain() = Pokemon(id, name, image, height, weight)