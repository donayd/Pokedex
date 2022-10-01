package com.pokedex.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pokedex.domain.GetPokemonsUseCase
import com.pokedex.domain.GetRandomPokemonUseCase
import com.pokedex.domain.model.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class PokemonViewModelTest {

    @RelaxedMockK
    private lateinit var getPokemonsUseCase: GetPokemonsUseCase

    @RelaxedMockK
    private lateinit var getRandomPokemonUseCase: GetRandomPokemonUseCase

    private lateinit var pokemonViewModel: PokemonViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        pokemonViewModel = PokemonViewModel(getPokemonsUseCase, getRandomPokemonUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When the viewmodel is create get pokemons and set Pikachu`() = runTest {

        //Given
        val myList = listOf(
            Pokemon(0, "Pikachu", "www.img.com", 10, 10),
            Pokemon(1, "Charizar", "www.img.com", 10, 10)
        )
        coEvery { getPokemonsUseCase() } returns myList

        //When
        pokemonViewModel.onCreate()

        //Them
        assert(pokemonViewModel.pokemonModel.value == myList.first())
    }

    @Test
    fun `When randomPokemon is call return pokemon of the livedata`() = runTest {

        //Given
        val pokemon = Pokemon(0, "Pikachu", "www.img.com", 10, 10)
        coEvery { getRandomPokemonUseCase() } returns pokemon

        //When
        pokemonViewModel.randomPokemon()

        //Them
        assert(pokemonViewModel.pokemonModel.value == pokemon)
    }

    @Test
    fun `When randomPokemon return null keep the last value`() = runTest {

        //Given
        val pokemon = Pokemon(0, "Pikachu", "www.img.com", 10, 10)
        pokemonViewModel.pokemonModel.value = pokemon
        coEvery { getRandomPokemonUseCase() } returns null

        //When
        pokemonViewModel.randomPokemon()

        //Them
        assert(pokemonViewModel.pokemonModel.value == pokemon)
    }

}