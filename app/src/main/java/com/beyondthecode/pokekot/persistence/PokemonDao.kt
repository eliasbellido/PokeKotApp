package com.beyondthecode.pokekot.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beyondthecode.pokekot.model.Pokemon

/**
 * Created by Elías Bellido on 7/14/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonList(pokemonList: List<Pokemon>)

    @Query("SELECT * FROM Pokemon WHERE page = :page_")
    fun getPokemonList(page_: Int): List<Pokemon>
}