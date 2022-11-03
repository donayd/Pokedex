package com.dreammkr.pokedex.data.model

import com.google.gson.annotations.SerializedName

class PokemonModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("stats") val stats: List<Stat>,
    @SerializedName("types") val types: List<TypeResponse>,
    @SerializedName("sprites") val image: ImageModel
)

data class Stat(
    @SerializedName("base_stat") val stat: Int
)

data class ImageModel(
    @SerializedName("front_default") val url_front: String,
    @SerializedName("back_default") val url_back: String
)

data class TypeResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: Type
)

data class Type(
    @SerializedName("name") val name: String
)