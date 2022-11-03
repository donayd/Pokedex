package com.dreammkr.pokedex.domain

import com.dreammkr.pokedex.data.PokemonRepository
import com.dreammkr.pokedex.domain.model.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetPokemonsUseCaseTest {

    @RelaxedMockK
    private lateinit var pokemonRepository: PokemonRepository

    lateinit var getPokemonsUseCase: GetPokemonsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPokemonsUseCase = GetPokemonsUseCase(pokemonRepository)
    }

    @Test
    fun `when the database doesnt return anything`() = runBlocking {

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
        coEvery { pokemonRepository.getAllPokemonFromApi() } returns myList
        coEvery { pokemonRepository.getAllPokemonFromDatabase() } returns emptyList()

        //When
        val response = getPokemonsUseCase()

        //Them
        coVerify(exactly = 1) { pokemonRepository.getAllPokemonFromApi() }
        coVerify(exactly = 1) { pokemonRepository.clearPokemons() }
        coVerify(exactly = 1) { pokemonRepository.insertPokemons(any()) }
        assert(myList == response)
    }

    @Test
    fun `when the database return something`() = runBlocking {

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
        val response = getPokemonsUseCase()

        //Them
        coVerify(exactly = 0) { pokemonRepository.getAllPokemonFromApi() }
        coVerify(exactly = 0) { pokemonRepository.clearPokemons() }
        coVerify(exactly = 0) { pokemonRepository.insertPokemons(any()) }
        assert(myList == response)
    }

}