package com.pokedex.data.network

import com.pokedex.data.model.RequestModel
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApiClient {

    @GET("pokemon")
    suspend fun getAllPokemon(): Response<RequestModel>

}