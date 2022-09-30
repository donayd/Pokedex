package com.pokedex.data.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey
import com.pokedex.domain.model.Pokemon

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int
)

fun Pokemon.toDatabase() = PokemonEntity(id, name, image, height, weight)