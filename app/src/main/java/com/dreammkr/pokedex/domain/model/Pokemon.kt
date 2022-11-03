package com.dreammkr.pokedex.domain.model

import com.dreammkr.pokedex.data.database.entities.PokemonEntity
import com.dreammkr.pokedex.data.model.PokemonModel

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val types: String,
    val image_front: String,
    val image_back: String,
)

fun PokemonModel.toDomain() = Pokemon(
    id, name, height, weight, stats[0].stat, stats[1].stat, stats[2].stat,
    stats[5].stat, types[0].type.name, image.url_front, image.url_back
)

fun PokemonEntity.toDomain() = Pokemon(
    id, name, height, weight, hp, attack,
    defense, speed, types, image_front, image_back
)