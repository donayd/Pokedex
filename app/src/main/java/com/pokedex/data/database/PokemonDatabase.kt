package com.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokedex.data.database.dao.PokemonDao
import com.pokedex.data.database.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

}