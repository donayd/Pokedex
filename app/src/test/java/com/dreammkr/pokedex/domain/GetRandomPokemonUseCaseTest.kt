package com.dreammkr.pokedex.domain

import com.dreammkr.pokedex.data.PokemonRepository
import com.dreammkr.pokedex.domain.model.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetRandomPokemonUseCaseTest {

    @RelaxedMockK
    private lateinit var pokemonRepository: PokemonRepository

    lateinit var getRandomPokemonUseCase: GetRandomPokemonUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomPokemonUseCase = GetRandomPokemonUseCase(pokemonRepository)
    }

    @Test
    fun `when the database is empty return null`() = runBlocking {

        //Given
        coEvery { pokemonRepository.getAllPokemonFromDatabase() } returns emptyList()

        //When
        val response = getRandomPokemonUseCase()

        //Them
        assert(response == null)
    }

    @Test
    fun `when the database is not empty return pokemon`() = runBlocking {

        //Given
        val myList = listOf(
            Pokemon(
                0,
                "Pikachu",
                100,
                100,
                100,
                100,
                100,
                100,
                "Normal",
                "www.img.com",
                "www.img.com"
            )
        )
        coEvery { pokemonRepository.getAllPokemonFromDatabase() } returns myList

        //When
        val response = getRandomPokemonUseCase()

        //Them
        assert(response == myList.first())
    }

}