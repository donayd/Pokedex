package com.pokedex.data.network

import com.pokedex.core.RetrofitHelper
import com.pokedex.data.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPokemon(): List<PokemonModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokemonApiClient::class.java).getAllPokemon()
            response.body()?.results ?: emptyList()
        }
    }

}