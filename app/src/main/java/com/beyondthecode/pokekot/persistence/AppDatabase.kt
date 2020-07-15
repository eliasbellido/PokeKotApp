package com.beyondthecode.pokekot.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.beyondthecode.pokekot.model.Pokemon
import com.beyondthecode.pokekot.model.PokemonInfo
import com.beyondthecode.pokekot.persistence.converters.InfoTypeConverter
import com.beyondthecode.pokekot.persistence.converters.InfoTypeResponseConverter

/**
 * Created by Elías Bellido on 7/13/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
@Database(entities = [
    Pokemon::class,
    PokemonInfo::class], version = 1, exportSchema = true)
@TypeConverters(value = [
    InfoTypeConverter::class,
    InfoTypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao
}