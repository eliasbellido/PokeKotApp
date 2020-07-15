package com.beyondthecode.pokekot.network

import com.beyondthecode.pokekot.model.PokemonInfo
import com.beyondthecode.pokekot.model.PokemonResponse
import com.skydoves.sandwich.DataSource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Elías Bellido on 7/13/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
interface PokedexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): DataSource<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ): DataSource<PokemonInfo>
}