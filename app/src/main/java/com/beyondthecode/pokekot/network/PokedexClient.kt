package com.beyondthecode.pokekot.network

import com.beyondthecode.pokekot.model.PokemonInfo
import com.beyondthecode.pokekot.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.toResponseDataSource
import javax.inject.Inject

/**
 * Created by Elías Bellido on 7/13/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
class PokedexClient @Inject constructor(
    private val pokedexService: PokedexService
){
    suspend fun fetchPokemonList(page: Int, onResult: (response: ApiResponse<PokemonResponse>) -> Unit){
        pokedexService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        ).toResponseDataSource()
            .retry(RETRY_COUNT, RETRY_DELAY)
            .request(onResult)
    }

    suspend fun fetchPokemonInfo(
        name: String,
        onResult: (response: ApiResponse<PokemonInfo>) -> Unit
    ){
        pokedexService.fetchPokemonInfo(name = name).toResponseDataSource()
            .retry(RETRY_COUNT, RETRY_DELAY)
            .request(onResult)
    }

    companion object{
        private const val PAGING_SIZE = 20
        private const val RETRY_COUNT = 3
        private const val RETRY_DELAY = 7000L
    }
}