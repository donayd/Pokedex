package com.pokedex.data.model

import com.google.gson.annotations.SerializedName

class PokemonModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val image: ImageModel,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int
)

class ImageModel(
    @SerializedName("front_default") val url: String
)