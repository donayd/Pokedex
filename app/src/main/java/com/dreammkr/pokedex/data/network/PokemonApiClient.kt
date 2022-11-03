package com.dreammkr.pokedex.data.network

import com.dreammkr.pokedex.data.model.PokemonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiClient {

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Response<PokemonModel>

    @GET("pokemon-species/{id}")
    suspend fun getPokemonDescription(@Path("id") id: Int): Response<PokemonModel>

}