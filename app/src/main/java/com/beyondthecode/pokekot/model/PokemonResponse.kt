package com.beyondthecode.pokekot.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Elías Bellido on 7/13/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "next") val next: String?,
    @field:Json(name = "previous") val previous: String?,
    @field:Json(name = "results") val results: List<Pokemon>
)