package com.pokedex.data.model

import com.google.gson.annotations.SerializedName

class RequestModel(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<PokemonModel>
)