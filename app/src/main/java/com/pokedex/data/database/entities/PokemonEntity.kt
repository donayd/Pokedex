package com.pokedex.data.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.pokedex.domain.model.Pokemon

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "hp") val hp: Int,
    @ColumnInfo(name = "attack") val attack: Int,
    @ColumnInfo(name = "defense") val defense: Int,
    @ColumnInfo(name = "speed") val speed: Int,
    @SerializedName("types") val types: String,
    @ColumnInfo(name = "image_front") val image_front: String,
    @ColumnInfo(name = "image_back") val image_back: String,
)

fun Pokemon.toDatabase() = PokemonEntity(
    id, name, height, weight, hp, attack,
    defense, speed, types, image_front, image_back
)