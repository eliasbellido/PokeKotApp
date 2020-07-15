package com.beyondthecode.pokekot.persistence.converters

import androidx.room.TypeConverter
import com.beyondthecode.pokekot.model.PokemonInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created by Elías Bellido on 7/13/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
open class InfoTypeResponseConverter {

    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): List<PokemonInfo.TypeResponse>? {
        val listType = Types.newParameterizedType(List::class.java, PokemonInfo.TypeResponse::class.java)
        val adapter: JsonAdapter<List<PokemonInfo.TypeResponse>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromInfoType(type: List<PokemonInfo.TypeResponse>?): String {
        val listType = Types.newParameterizedType(List::class.java, PokemonInfo.TypeResponse::class.java)
        val adapter: JsonAdapter<List<PokemonInfo.TypeResponse>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}